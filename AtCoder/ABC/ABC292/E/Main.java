package AtCoder.ABC.ABC292.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

/*
到達可能な頂点には辺を張ることになる
 */
public class Main {

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final MultiValueMap<Integer, Integer> parentToChildMap = new MultiValueMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            parentToChildMap.add(u, v);
        }

        final BiConsumer<Integer, boolean[]> recursive = new BiConsumer<>() {

            @Override
            public void accept(final Integer node, final boolean[] isVisited) {
                isVisited[node] = true;
                for (final Integer next : parentToChildMap.get(node)) {
                    if (!isVisited[next]) {
                        accept(next, isVisited);
                    }
                }
            }
        };

        long sum = 0;
        for (int i = 1; i <= n; i++) {
            final boolean[] isVisited = new boolean[n + 1];
            recursive.accept(i, isVisited);
            sum += IntStream.rangeClosed(1, n).filter(j -> isVisited[j]).count() - 1; // remove itself
        }

        System.out.println(sum - m);
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
