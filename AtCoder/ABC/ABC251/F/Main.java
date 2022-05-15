package AtCoder.ABC.ABC251.F;

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
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final ListMap<Integer, Integer> graph = new ListMap<>();

        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.putSingle(u, v);
            graph.putSingle(v, u);
        }

        System.out.println(buildTree(solveT1(graph, n)));
        System.out.println(buildTree(solveT2(graph, n)));
    }

    private static int[] solveT1(final ListMap<Integer, Integer> graph, final int n) {
        final int[] parents = new int[n + 1];
        Arrays.fill(parents, -1);
        parents[1] = 0;
        final Consumer<Integer> dfs = new Consumer<>() {
            @Override
            public void accept(final Integer current) {
                for (final int next : graph.getList(current)) {
                    if (parents[next] != -1) {
                        continue;
                    }
                    parents[next] = current;
                    accept(next);
                }
            }
        };
        dfs.accept(1);

        return parents;
    }

    private static int[] solveT2(final ListMap<Integer, Integer> graph, final int n) {
        final int[] parents = new int[n + 1];
        Arrays.fill(parents, -1);
        parents[1] = 0;
        final Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            final int current = queue.pollFirst();
            for (final int next : graph.getList(current)) {
                if (parents[next] != -1) {
                    continue;
                }
                parents[next] = current;
                queue.addLast(next);
            }
        }

        return parents;
    }

    private static String buildTree(final int[] array) {
        return IntStream.range(2, array.length)
            .mapToObj(i -> array[i] + " " + i)
            .collect(Collectors.joining("\n"));
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
