package other.past_1.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
bitDPみたいな
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int binary = (int) Math.pow(2, n);
        final long[][] dp = new long[m][binary];
        for (final long[] array : dp) {
            Arrays.fill(array, Long.MAX_VALUE / 2);
        }

        final int initialBinary = convertBinary(scanner.next());
        dp[0][initialBinary] = scanner.nextInt();
        for (int i = 1; i < m; i++) {
            System.arraycopy(dp[i - 1], 0, dp[i], 0, binary);
            final int tmp = convertBinary(scanner.next());
            final long c = scanner.nextInt();
            dp[i][tmp] = Math.min(dp[i][tmp], c);
            for (int j = 1; j < binary; j++) {
                final int or = j | tmp;
                dp[i][or] = Math.min(dp[i][or], dp[i - 1][j] + c);
            }
        }

        if (dp[m - 1][binary - 1] < Long.MAX_VALUE / 2) {
            System.out.println(dp[m - 1][binary - 1]);
        } else {
            System.out.println(-1);
        }
    }

    private static int convertBinary(final String str) {
        int base = 1;
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'Y') {
                sum += base;
            }
            base *= 2;
        }
        return sum;
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

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken("\n");
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
