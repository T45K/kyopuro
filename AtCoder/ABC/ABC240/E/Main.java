package AtCoder.ABC.ABC240.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static int count = 1;

    @SuppressWarnings("unchecked")
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final ListMap<Integer, Integer> tree = new ListMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            tree.putSingle(u, v);
            tree.putSingle(v, u);
        }
        final Pair<Integer, Integer>[] array = new Pair[n + 1];
        dfs(1, 0, array, tree);
        final String answer = IntStream.rangeClosed(1, n)
            .mapToObj(i -> array[i])
            .map(it -> it.first + " " + it.second)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static void dfs(final int current, final int parent, final Pair<Integer, Integer>[] array, final ListMap<Integer, Integer> tree) {
        final List<Integer> list = tree.getList(current);
        if (list.size() == 1 && current != 1) {
            array[current] = new Pair<>(count, count);
            count++;
            return;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (final int next : list) {
            if (next == parent) {
                continue;
            }
            dfs(next, current, array, tree);
            min = Math.min(min, array[next].first);
            max = Math.max(max, array[next].second);
        }
        array[current] = new Pair<>(min, max);
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
