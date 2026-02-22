package ABC.ABC191.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            map.computeIfAbsent(a, v -> new HashMap<>())
                .compute(b, (k, v) -> v == null ? c : Math.min(c, v));
        }
        for (int i = 1; i <= n; i++) {
            dijkstra(map, i, n);
        }
    }

    private static void dijkstra(final Map<Integer, Map<Integer, Integer>> map, final int i, final int n) {
        final int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        final PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparingInt(r -> r.cost));
        final BiConsumer<Integer, Integer> updateQueue = (from, baseCost) ->
            Optional.ofNullable(map.get(from))
                .orElse(Collections.emptyMap())
                .forEach((to, cost) -> {
                    final int nextCost = baseCost + cost;
                    if (distances[to] > nextCost) {
                        distances[to] = nextCost;
                        queue.add(new Route(to, nextCost));
                    }
                });
        updateQueue.accept(i, 0);
        while (!queue.isEmpty()) {
            final int from = queue.poll().to;
            updateQueue.accept(from, distances[from]);
        }
        System.out.println(distances[i] < Integer.MAX_VALUE ? distances[i] : -1);
    }

    private static class Route {
        final int to;
        final int cost;

        Route(final int to, final int cost) {
            this.to = to;
            this.cost = cost;
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
