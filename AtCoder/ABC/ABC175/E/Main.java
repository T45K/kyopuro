package AtCoder.ABC.ABC175.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
愚直DP
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int r = scanner.nextInt();
        final int c = scanner.nextInt();
        final int k = scanner.nextInt();

        final int[][] values = new int[r + 1][c + 1];
        for (int i = 0; i < k; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            final int v = scanner.nextInt();
            values[x][y] = v;
        }

        final long[][][] dp = new long[r + 1][c + 1][4];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                final int aboveValue = values[i - 1][j];
                final int leftValue = values[i][j - 1];
                final long maxAbove = max(dp[i - 1][j][0], dp[i - 1][j][1], dp[i - 1][j][2]);
                dp[i][j][0] = max(dp[i][j - 1][0], maxAbove + aboveValue, dp[i - 1][j][3]);
                dp[i][j][1] = max(dp[i][j - 1][0] + leftValue, dp[i][j - 1][1]);
                dp[i][j][2] = max(dp[i][j - 1][1] + leftValue, dp[i][j - 1][2]);
                dp[i][j][3] = max(dp[i][j - 1][2] + leftValue, dp[i][j - 1][3]);
            }
        }

        System.out.println(max(dp[r][c][0] + values[r][c], dp[r][c][1] + values[r][c], dp[r][c][2] + values[r][c], dp[r][c][3]));
    }

    private static long max(final long... values) {
        Arrays.sort(values);
        return values[values.length - 1];
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
    