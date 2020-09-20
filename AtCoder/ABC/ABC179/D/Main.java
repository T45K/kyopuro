package AtCoder.ABC.ABC179.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// DP?
public class Main {
    private static final long MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Pair> list = IntStream.range(0, k)
            .mapToObj(i -> new Pair(scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());

        final long[] dp = new long[n + 1];
        dp[1] = 1;
        long current = 0;
        for (int i = 1; i <= n; i++) {
            current += dp[i];
            current %= MOD;
            for (final Pair pair : list) {
                if (pair.l + i <= n) {
                    dp[pair.l + i] += current;
                    dp[pair.l + i] %= MOD;
                }
                if (pair.r + i < n) {
                    dp[pair.r + i + 1] += MOD;
                    dp[pair.r + i + 1] -= current;
                    dp[pair.l + i + 1] %= MOD;
                }
            }
        }

        System.out.println(dp[n]);
    }

    private static class Pair {
        final int l;
        final int r;

        Pair(final int l, final int r) {
            this.l = l;
            this.r = r;
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
    