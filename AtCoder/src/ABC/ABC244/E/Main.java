package ABC.ABC244.E;

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

public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();
        final int s = scanner.nextInt();
        final int t = scanner.nextInt();
        final int x = scanner.nextInt();

        final ListMap<Integer, Integer> graph = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.putSingle(u, v);
            graph.putSingle(v, u);
        }

        final long[][] even = new long[k + 1][n + 1];
        final long[][] odd = new long[k + 1][n + 1];
        even[0][s] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == x) {
                    continue;
                }
                for (final int previous : graph.getList(j)) {
                    even[i][j] += even[i - 1][previous];
                    even[i][j] %= MOD;
                    odd[i][j] += odd[i - 1][previous];
                    odd[i][j] %= MOD;
                }
            }
            for (final int previous : graph.getList(x)) {
                even[i][x] += odd[i - 1][previous];
                even[i][x] %= MOD;
                odd[i][x] += even[i - 1][previous];
                odd[i][x] %= MOD;
            }
        }
        System.out.println(even[k][t]);
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
