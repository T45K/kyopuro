package AtCoder.ABC.ABC267.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// TODO: solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();

        final long[][] dp = new long[n][m + 1];
        for (long[] a : dp) {
            Arrays.fill(a, 1, m + 1, Integer.MIN_VALUE / 2);
        }
        dp[0][1] = array[0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= Math.min(m, i + 1); j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + (long) j * array[i]);
            }
        }

        System.out.println(dp[n - 1][m]);
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
