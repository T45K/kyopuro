package ABC.ABC224.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
ちょっと複雑なダイクストラ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int m = scanner.nextInt();
        final SetMap<Integer, Integer> graph = new SetMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.putSingle(u, v);
            graph.putSingle(v, u);
        }
        final int[] array = new int[10];
        for (int i = 1; i <= 8; i++) {
            final int p = scanner.nextInt();
            array[p] = i;
        }
        final Position position = new Position(array);
        final Map<Position, Integer> distances = new HashMap<>();
        distances.put(position, 0);
        final PriorityQueue<Position> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(position);

        while (!queue.isEmpty()) {
            final Position poll = queue.poll();
            final int distance = distances.get(poll);
            if (IntStream.rangeClosed(1, 8).allMatch(i -> poll.value[i] == i)) {
                System.out.println(distance);
                return;
            }
            final int emptyPosition = IntStream.range(1, 10)
                .filter(i -> poll.value[i] == 0)
                .findFirst()
                .orElseThrow();
            for (int i = 1; i <= 9; i++) {
                if (i == emptyPosition) {
                    continue;
                }
                if (!graph.getSet(i).contains(emptyPosition)) {
                    continue;
                }
                final int value = poll.value[i];
                final int[] copy = new int[10];
                System.arraycopy(poll.value, 0, copy, 0, 10);
                copy[emptyPosition] = value;
                copy[i] = 0;
                final Position next = new Position(copy);
                if (!distances.containsKey(next)) {
                    distances.put(next, distance + 1);
                    queue.add(next);
                }
            }
        }

        System.out.println(-1);
    }

    private static class Position {
        final int[] value;

        Position(final int[] value) {
            this.value = value;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Position position = (Position) o;
            return Arrays.equals(value, position.value);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(value);
        }
    }

    private static class SetMap<K, V> extends HashMap<K, Set<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new HashSet<>()).add(value);
        }

        public Set<V> getSet(final K key) {
            return super.getOrDefault(key, Collections.emptySet());
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
