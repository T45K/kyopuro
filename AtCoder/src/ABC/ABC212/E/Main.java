package ABC.ABC212.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

/*
もらうDP
問題文で欠損した辺について言及されてるので，総和から引くことを考える
 */
public class Main {
    private static final long MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();
        final ListMap<Integer, Integer> map = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            map.putSingle(u, v);
            map.putSingle(v, u);
        }

        final long[][] dp = new long[k + 1][n + 1];
        dp[0][1] = 1;
        for (int i = 1; i <= k; i++) {
            final long sum = Arrays.stream(dp[i - 1]).reduce(0, (a, b) -> (a + b) % MOD);
            for (int j = 1; j <= n; j++) {
                dp[i][j] = sum;
                dp[i][j] = subMod(dp[i][j], dp[i - 1][j]);
                for (final int value : map.getList(j)) {
                    dp[i][j] = subMod(dp[i][j], dp[i - 1][value]);
                }
            }
        }
        System.out.println(dp[k][1]);
    }

    private static long subMod(final long a, final long b) {
        return (a - b + MOD) % MOD;
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
