package AtCoder.ABC.ABC237.E;

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
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// TODO fix
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final long[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToLong(Integer::longValue)
            .toArray();
        final ListMap<Integer, Integer> graph = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt() - 1;
            final int v = scanner.nextInt() - 1;
            graph.putSingle(u, v);
            graph.putSingle(v, u);
        }

        final long[] distances = new long[n];
        Arrays.fill(distances, Long.MIN_VALUE);
        distances[0] = 0;
        // final TreeSet<Pair<Integer, Long>> queue = new TreeSet<>(Comparator.comparing(it -> it.second, Comparator.reverseOrder()));
        final PriorityQueue<Pair<Integer, Long>> queue = new PriorityQueue<>(Comparator.comparing(it -> it.second, Comparator.reverseOrder()));
        for (final int next : graph.getList(0)) {
            distances[next] = (array[0] >= array[next] ? 1 : 2) * (array[0] - array[next]);
            queue.add(new Pair<>(next, distances[next]));
        }
        while (!queue.isEmpty()) {
            final Pair<Integer, Long> poll = queue.poll();
            if (poll.second < distances[poll.first]) {
                continue;
            }
            distances[poll.first] = poll.second;
            for (final int next : graph.getList(poll.first)) {
                final long value = (array[poll.first] >= array[next] ? 1 : 2) * (array[poll.first] - array[next]);
                if (distances[poll.first] + value > distances[next]) {
                    distances[next] = distances[poll.first] + value;
                    queue.add(new Pair<>(next, distances[next]));
                }
            }
        }

        final long answer = Arrays.stream(distances)
            .max()
            .orElseThrow();
        System.out.println(answer);
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

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
