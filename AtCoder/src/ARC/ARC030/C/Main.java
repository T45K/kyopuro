package ARC.ARC030.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
木 N<=100からワーシャルフロイドをしたくなるが実は単純なDFS
N=1の場合があることに注意
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(0);
            return;
        }
        final int x = scanner.nextInt();
        final Set<Integer> destinations = IntStream.rangeClosed(1, n)
                .filter(i -> scanner.nextInt() == 1)
                .boxed()
                .collect(Collectors.toSet());

        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            tree.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        final boolean[] isVisited = new boolean[n + 1];
        final int answer = Math.max(recursive(tree, x, isVisited, destinations) - 2, 0);
        System.out.println(answer);
    }

    private static int recursive(final Map<Integer, List<Integer>> tree, final int current, final boolean[] isVisited, final Set<Integer> destinations) {
        isVisited[current] = true;
        int sum = 0;
        for (final int next : tree.get(current)) {
            if (isVisited[next]) {
                continue;
            }
            sum += recursive(tree, next, isVisited, destinations);
        }

        if (sum > 0 || destinations.contains(current)) {
            return sum + 2;
        } else {
            return 0;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
