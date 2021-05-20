package AtCoder.other.typecal90.Z026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        final int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);
        distances[1] = 0;
        dfs(tree, 1, distances);
        final long evenDistances = Arrays.stream(distances, 1, n + 1)
            .filter(i -> i % 2 == 0)
            .count();
        final String answer = IntStream.rangeClosed(1, n)
            .filter(i -> distances[i] % 2 == (evenDistances >= n / 2 ? 0 : 1))
            .limit(n / 2)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" "));
        System.out.println(answer);
    }

    private static void dfs(final Map<Integer, List<Integer>> tree, final int current, final int[] distances) {
        for (final int next : tree.get(current)) {
            if (distances[next] == -1) {
                distances[next] = distances[current] + 1;
                dfs(tree, next, distances);
            }
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
