package ABC.ABC213.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;

/*
Javaは再帰関数をスタックするので引数が多いと遅い？
 */
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

        final StringJoiner joiner = new StringJoiner(" ");
        joiner.add("1");
        final BiConsumer<Integer, Integer> dfs = new BiConsumer<>() {
            @Override
            public void accept(final Integer current, final Integer parent) {
                Collections.sort(tree.getList(current));
                for (final int next : tree.getList(current)) {
                    if (next == parent) {
                        continue;
                    }
                    joiner.add(Integer.toString(next));
                    this.accept(next, current);
                    joiner.add(Integer.toString(current));
                }
            }
        };
        dfs.accept(1, 0);
        System.out.println(joiner);
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
    