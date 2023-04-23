package AtCoder.ABC.ABC299.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final MultiValueMap<Integer, Integer> graph = new MultiValueMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.add(u, v);
            graph.add(v, u);
        }
        final int k = scanner.nextInt();
        final Map<Integer, Integer> limitations = new LinkedHashMap<>();
        for (int i = 0; i < k; i++) {
            final int p = scanner.nextInt();
            final int d = scanner.nextInt();
            limitations.put(p, d);
        }

        final int[][] allDistances = Stream.concat(
            Stream.of(new int[n + 1]),
            IntStream.rangeClosed(1, n)
                .mapToObj(i -> {
                    final int[] distances = new int[n + 1];
                    Arrays.fill(distances, -1);
                    traverse(i, distances, graph);
                    return distances;
                })
        ).toArray(int[][]::new);

        final boolean[] mustWhite = new boolean[n + 1];
        for (final Map.Entry<Integer, Integer> entry : limitations.entrySet()) {
            final int from = entry.getKey();
            final int distance = entry.getValue();
            for (int to = 1; to <= n; to++) {
                if (allDistances[from][to] < distance) {
                    mustWhite[to] = true;
                }
            }
        }

        final boolean possible = limitations.entrySet().stream().allMatch(entry -> {
            final int from = entry.getKey();
            final int distance = entry.getValue();
            return IntStream.rangeClosed(1, n)
                .filter(to -> allDistances[from][to] == distance)
                .anyMatch(i -> !mustWhite[i]);
        });
        if (possible) {
            System.out.println("Yes");
            System.out.println(IntStream.rangeClosed(1, n).mapToObj(i -> mustWhite[i] ? "0" : "1").collect(Collectors.joining()));
        } else {
            System.out.println("No");
        }
    }

    private static void traverse(final int start, final int[] distances, final MultiValueMap<Integer, Integer> graph) {
        distances[start] = 0;
        final Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            final int current = queue.poll();
            for (final int next : graph.get(current)) {
                if (distances[next] == -1) {
                    distances[next] = distances[current] + 1;
                    queue.add(next);
                }
            }
        }
    }

    private static class MultiValueMap<K, V> extends HashMap<K, List<V>> {

        public void add(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        @Override
        public List<V> get(final Object key) {
            return super.getOrDefault(key, Collections.emptyList());
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
