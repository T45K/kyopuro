package AtCoder.ABC.ABC237.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * 解説AC
 */
public class Main {
    private static final long MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final long[][][][] dp = new long[n + 1][m + 2][m + 2][m + 2];
        dp[0][m + 1][m + 1][m + 1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int a1 = 1; a1 <= m + 1; a1++) {
                for (int a2 = 1; a2 <= m + 1; a2++) {
                    for (int a3 = 1; a3 <= m + 1; a3++) {
                        for (int j = 1; j <= m; j++) {
                            if (j <= a1) {
                                dp[i][j][a2][a3] += dp[i - 1][a1][a2][a3];
                                dp[i][j][a2][a3] %= MOD;
                            } else if (j <= a2) {
                                dp[i][a1][j][a3] += dp[i - 1][a1][a2][a3];
                                dp[i][a1][j][a3] %= MOD;
                            } else if (j <= a3) {
                                dp[i][a1][a2][j] += dp[i - 1][a1][a2][a3];
                                dp[i][a1][a2][j] %= MOD;
                            }
                        }
                    }
                }
            }
        }

        final Long answer = IntStream.rangeClosed(1, m)
            .boxed()
            .flatMap(a1 -> IntStream.rangeClosed(a1 + 1, m)
                .boxed()
                .flatMap(a2 -> IntStream.rangeClosed(a2 + 1, m)
                    .boxed()
                    .map(a3 -> dp[n][a1][a2][a3])
                )
            ).reduce((a, b) -> (a + b) % MOD).orElseThrow();
        System.out.println(answer);
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
