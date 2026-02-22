package other.indeednow_2015_qual.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
木 dfs+優先度付きキュー
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            tree.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        final List<Integer> list = new ArrayList<>(n);
        final boolean[] isVisited = new boolean[n + 1];
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            final int poll = queue.poll();
            if (isVisited[poll]) {
                continue;
            }
            isVisited[poll] = true;
            list.add(poll);
            tree.get(poll).stream()
                    .filter(i -> !isVisited[i])
                    .forEach(queue::add);
        }

        final String answer = list.stream()
                .map(i -> Integer.toString(i))
                .collect(Collectors.joining(" "));

        System.out.println(answer);
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
