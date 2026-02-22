package ABC.ABC218.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
最初に最短経路を求め，それに含まれる経路に対して，それぞれ通れない際の最短経路を求める
幅探で良い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final ListMap<Integer, Integer> graph = new ListMap<>();
        final List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            final int s = scanner.nextInt();
            final int t = scanner.nextInt();
            graph.putSingle(s, t);
            edges.add(new Edge(s, t));
        }

        final InitialDijkstraResult initialDijkstraResult = initialDijkstra(n, graph);
        if (initialDijkstraResult.cost == -1) {
            final String answer = Stream.generate(() -> "-1")
                .limit(m)
                .collect(Collectors.joining("\n"));
            System.out.println(answer);
            return;
        }

        final StringJoiner joiner = new StringJoiner("\n");
        for (final Edge edge : edges) {
            if (initialDijkstraResult.set.contains(edge)) {
                final int distance = dijkstra(n, graph, edge.from, edge.to);
                joiner.add(Integer.toString(distance));
            } else {
                joiner.add(Integer.toString(initialDijkstraResult.cost));
            }
        }
        System.out.println(joiner);
    }

    private static InitialDijkstraResult initialDijkstra(final int n, final ListMap<Integer, Integer> graph) {
        final int[] distances = new int[n + 1];
        Arrays.fill(distances, 401);
        distances[1] = 0;
        final int[] prev = new int[n + 1];
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            final int currentPosition = queue.poll();
            final int currentDistance = distances[currentPosition];
            for (final int next : graph.getList(currentPosition)) {
                if (distances[next] <= currentDistance + 1) {
                    continue;
                }
                distances[next] = currentDistance + 1;
                prev[next] = currentPosition;
                queue.add(next);
            }
        }

        if (distances[n] == 401) {
            return new InitialDijkstraResult(-1, Collections.emptySet());
        }

        final Set<Edge> set = new HashSet<>();
        int tmp = n;
        while (tmp != 1) {
            set.add(new Edge(prev[tmp], tmp));
            tmp = prev[tmp];
        }

        return new InitialDijkstraResult(distances[n], set);
    }


    private static int dijkstra(final int n, final ListMap<Integer, Integer> graph, final int notUsedFrom, final int notUsedTo) {
        final int[] distances = new int[n + 1];
        Arrays.fill(distances, 401);
        distances[1] = 0;
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            final int currentPosition = queue.poll();
            final int currentDistance = distances[currentPosition];
            for (final int next : graph.getList(currentPosition)) {
                if (currentPosition == notUsedFrom && next == notUsedTo || distances[next] <= currentDistance + 1) {
                    continue;
                }
                distances[next] = currentDistance + 1;
                queue.add(next);
            }
        }

        if (distances[n] == 401) {
            return -1;
        } else {
            return distances[n];
        }
    }

    private static class InitialDijkstraResult {
        final int cost;
        final Set<Edge> set;

        InitialDijkstraResult(final int cost, final Set<Edge> list) {
            this.cost = cost;
            this.set = list;
        }
    }

    private static class Edge {
        final int from;
        final int to;

        Edge(final int from, final int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
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
