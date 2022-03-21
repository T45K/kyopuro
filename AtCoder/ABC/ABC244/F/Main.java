package AtCoder.ABC.ABC244.F;

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

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final ListMap<Integer, Integer> graph = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.putSingle(u - 1, v - 1);
            graph.putSingle(v - 1, u - 1);
        }

        final int[][] table = new int[1 << n][n];
        for (final int[] array : table) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        table[0][0] = 0;
        final PriorityQueue<Path> queue = new PriorityQueue<>(Comparator.comparingInt(path -> path.distance));
        for (int i = 0; i < n; i++) {
            table[1 << i][i] = 1;
            queue.add(new Path(1 << i, i, 1));
        }

        while (!queue.isEmpty()) {
            final Path poll = queue.poll();
            if (poll.distance > table[poll.bit][poll.index]) {
                continue;
            }
            table[poll.bit][poll.index] = poll.distance;
            for (final int nextIndex : graph.getList(poll.index)) {
                final int nextBit = poll.bit ^ (1 << nextIndex);
                if (poll.distance + 1 < table[nextBit][nextIndex]) {
                    table[nextBit][nextIndex] = poll.distance + 1;
                    queue.add(new Path(nextBit, nextIndex, poll.distance + 1));
                }
            }
        }

        final int answer = Arrays.stream(table)
            .mapToInt(array -> Arrays.stream(array).min().orElseThrow())
            .sum();
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

    private static class Path {
        final int bit;
        final int index;
        final int distance;

        public Path(final int bit, final int index, final int distance) {
            this.bit = bit;
            this.index = index;
            this.distance = distance;
        }
    }
}
