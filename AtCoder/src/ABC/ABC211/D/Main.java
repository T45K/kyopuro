package ABC.ABC211.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
ダイクストラに経路の数を持たせる
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final ListMap<Integer, Integer> graph = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            graph.putSingle(a, b);
            graph.putSingle(b, a);
        }

        final long answer = dijkstra(n, graph);
        System.out.println(answer);
    }

    private static long dijkstra(final int n, final ListMap<Integer, Integer> graph) {
        final long[] distances = new long[n + 1];
        final long[] combinations = new long[n + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[1] = 0;
        combinations[1] = 1;

        final PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparingInt(r -> r.cost));
        for (final int next : graph.getList(1)) {
            queue.add(new Route(1, next, 1));
        }

        while (!queue.isEmpty()) {
            final Route poll = queue.poll();
            if (poll.cost > distances[poll.to]) {
                continue;
            }

            if (poll.cost == distances[poll.to]) {
                combinations[poll.to] += combinations[poll.from];
                combinations[poll.to] %= MOD;
                continue;
            }

            distances[poll.to] = poll.cost;
            combinations[poll.to] = combinations[poll.from];
            for (final int next : graph.getList(poll.to)) {
                if (poll.cost + 1 <= distances[next]) {
                    queue.add(new Route(poll.to, next, poll.cost + 1));
                }
            }
        }

        return combinations[n];
    }

    private static class Route {
        final int from;
        final int to;
        final int cost;

        Route(final int from, final int to, final int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static class ListMap<K, V> extends HashMap<K, List<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public List<V> getList(final K key) {
            return Optional.ofNullable(super.get(key)).orElse(Collections.emptyList());
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
