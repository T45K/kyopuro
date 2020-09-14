package AtCoder.ABC.ABC178.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
DP
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[][] dp = new long[n + 1][4];// 0 -> ok, 1 -> 0-included, 2 -> 9-included, 3 -> ng
        dp[1][0] = 0;
        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 8;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] * 10 + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][1] * 9 + dp[i - 1][3]) % MOD;
            dp[i][2] = (dp[i - 1][2] * 9 + dp[i - 1][3]) % MOD;
            dp[i][3] = dp[i - 1][3] * 8 % MOD;
        }

        System.out.println(dp[n][0]);
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
