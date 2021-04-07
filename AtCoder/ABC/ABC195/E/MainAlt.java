package AtCoder.ABC.ABC195.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainAlt {
    private static final String TAKAHASHI = "Takahashi";
    private static final String AOKI = "Aoki";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final char[] s = scanner.next().toCharArray();
        final char[] x = scanner.next().toCharArray();
        final boolean[][] dp = new boolean[n + 1][7];
        dp[n][0] = true;
        for (int i = n - 1; i >= 0; i--) {
            final int value = s[i] - '0';
            for (int j = 0; j < 7; j++) {
                final boolean nextWithoutValue = dp[i + 1][10 * j % 7];
                final boolean nextWithValue = dp[i + 1][(10 * j + value) % 7];
                dp[i][j] = x[i] == 'T' ? nextWithoutValue || nextWithValue
                    : nextWithoutValue && nextWithValue;
            }
        }
        if (dp[0][0]) {
            System.out.println(TAKAHASHI);
        } else {
            System.out.println(AOKI);
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
