package AtCoder.ABC.ABC213.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// TODO
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final ListMap<Integer, Integer> tree = new ListMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.putSingle(a, b);
            tree.putSingle(b, a);
        }

        for (final List<Integer> list : tree.values()) {
            Collections.sort(list);
        }

        final StringJoiner joiner = new StringJoiner(" ");
        joiner.add("1");
        dfs(1, 0, tree, joiner);
        System.out.println(joiner);
    }

    private static void dfs(final int current, final int parent, final ListMap<Integer, Integer> tree, final StringJoiner joiner) {
        for (final int next : tree.getList(current)) {
            if (next == parent) {
                continue;
            }
            joiner.add(Integer.toString(next));
            dfs(next, current, tree, joiner);
            joiner.add(Integer.toString(current));
        }
    }

    private static class ListMap<K, V> extends LinkedHashMap<K, List<V>> {
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
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    