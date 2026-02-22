package ABC.ABC253.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
いもす法で計算時間を抑える
 */
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();

        final long[][] sumDp = new long[n][m + 1];
        final long[][] upDp = new long[n][m + 1];
        final long[][] downDp = new long[n][m + 1];
        for (int i = 1; i <= m; i++) {
            sumDp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (k == 0) {
                    downDp[i][m] += sumDp[i - 1][j];
                    downDp[i][m] %= MOD;
                    continue;
                }
                if (j - k >= 1) {
                    downDp[i][j - k] += sumDp[i - 1][j];
                    downDp[i][j - k] %= MOD;
                }
                if (j + k <= m) {
                    upDp[i][j + k] += sumDp[i - 1][j];
                    upDp[i][j + k] %= MOD;
                }
            }
            // accumulate
            for (int j = 2; j <= m; j++) {
                upDp[i][j] += upDp[i][j - 1];
                upDp[i][j] %= MOD;
            }
            for (int j = m - 1; j >= 1; j--) {
                downDp[i][j] += downDp[i][j + 1];
                downDp[i][j] %= MOD;
            }

            // sum up
            for (int j = 1; j <= m; j++) {
                sumDp[i][j] = upDp[i][j] + downDp[i][j];
                sumDp[i][j] %= MOD;
            }
        }

        final long answer = Arrays.stream(sumDp[n - 1], 1, m + 1)
            .reduce((lhs, rhs) -> (lhs + rhs) % MOD)
            .orElseThrow();
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
