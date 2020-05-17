package AtCoder.ABC.ABC168.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
グラフ bfsするだけ 読解力コンテスト
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        final int[] routes = new int[n + 1];
        Arrays.fill(routes, 0);
        routes[1] = -1;

        final Deque<Edge> queue = graph.get(1).stream().map(i -> new Edge(1, i)).collect(Collectors.toCollection(ArrayDeque::new));
        while (!queue.isEmpty()) {
            final Edge dest = queue.pollFirst();
            if (routes[dest.to] > 0) {
                continue;
            }

            routes[dest.to] = dest.from;
            queue.addAll(
                graph.get(dest.to).stream()
                    .filter(i -> routes[i] == 0)
                    .map(i -> new Edge(dest.to, i))
                    .collect(Collectors.toList())
            );
        }

        final boolean visitable = Arrays.stream(routes, 2, n + 1).allMatch(route -> route > 0);
        if (!visitable) {
            System.out.println("No");
        }

        System.out.println("Yes");
        Arrays.stream(routes, 2, n + 1)
            .forEach(System.out::println);
    }

    private static class Edge {
        final int from;
        final int to;

        Edge(final int from, final int to) {
            this.from = from;
            this.to = to;
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
