package ABC.ABC252.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
ダイクストラ法の要領で最短経路を求めていく
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final ListMap<Integer, Edge> graph = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            graph.putSingle(a, new Edge(i + 1, a, b, c));
            graph.putSingle(b, new Edge(i + 1, b, a, c));
        }

        final PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(edge -> edge.cost));
        for (final Edge next : graph.getList(1)) {
            queue.add(new Edge(next.index, 1, next.to, next.cost));
        }

        final StringJoiner edgeIndices = new StringJoiner(" ");
        final boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        while (!queue.isEmpty()) {
            final Edge poll = queue.poll();
            if (visited[poll.to]) {
                continue;
            }
            visited[poll.to] = true;
            edgeIndices.add(Integer.toString(poll.index));
            for (final Edge next : graph.getList(poll.to)) {
                if (visited[next.to]) {
                    continue;
                }
                queue.add(new Edge(next.index, poll.to, next.to, poll.cost + next.cost));
            }
        }

        System.out.println(edgeIndices);
    }

    private static class Edge {
        final int index;
        final int from;
        final int to;
        final long cost;

        Edge(final int index, final int from, final int to, final long cost) {
            this.index = index;
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
