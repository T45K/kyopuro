package AtCoder.ABC.ABC222.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] aArray = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final int[] bArray = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();

        final long[][] dp = new long[n][3001];
        for (int i = aArray[0]; i <= bArray[0]; i++) {
            dp[0][i]++;
        }
        for (int i = 1; i < n; i++) {
            final int begin = aArray[i];
            final long sum = Arrays.stream(dp[i - 1], 0, begin).reduce(0, (a, b) -> (a + b) % MOD);
            dp[i][begin] = (sum + dp[i - 1][begin]) % MOD;
            for (int j = begin + 1; j <= bArray[i]; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        final long answer = Arrays.stream(dp[n - 1]).reduce(0, (a, b) -> (a + b) % MOD);
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
