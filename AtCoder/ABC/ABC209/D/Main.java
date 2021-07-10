package AtCoder.ABC.ABC209.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
距離の偶奇を求める問題だが，木構造なので頂点からの距離の差を求めればよい
 */
public class Main {
    private static final String TOWN = "Town";
    private static final String ROAD = "Road";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final ListMap<Integer, Integer> tree = new ListMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.putSingle(a, b);
            tree.putSingle(b, a);
        }

        final int[] distances = calculateDistances(tree, n);

        final String answer = Stream.generate(() -> {
            final int c = scanner.nextInt();
            final int d = scanner.nextInt();
            return Math.abs(distances[c] - distances[d]) % 2 == 0 ? TOWN : ROAD;
        })
            .limit(q)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static int[] calculateDistances(final ListMap<Integer, Integer> tree, final int n) {
        final int[] distances = new int[n + 1];
        distances[0] = -1;
        final BiConsumer<Integer, Integer> bfs = new BiConsumer<>() {
            @Override
            public void accept(final Integer current, final Integer parent) {
                for (final int next : tree.getList(current)) {
                    if (next == parent) {
                        continue;
                    }
                    accept(next, current);
                }
            }
        };
        bfs.accept(1, 0);
        return distances;
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
