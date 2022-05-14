package AtCoder.ABC.ABC251.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] feedPrices = Stream.generate(scanner::nextLong)
            .limit(n)
            .mapToLong(Long::longValue)
            .toArray();

        System.out.println(Math.min(feedFirst(n, feedPrices), feedLast(n, feedPrices)));
    }

    private static long feedFirst(final int n, final long[] feedPrices) {
        final long[][] dp = new long[n][2]; // 0: 右にも餌をやる、1: 左に餌をやっている
        dp[0][0] = feedPrices[0];
        dp[0][1] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + feedPrices[i];
            dp[i][1] = dp[i - 1][0];
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    private static long feedLast(final int n, final long[] feedPrices) {
        final long[][] dp = new long[n][2]; // 0: 右にも餌をやる、1: 左に餌をやっている
        dp[0][0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + feedPrices[i];
            dp[i][1] = dp[i - 1][0];
        }

        return dp[n - 1][0];
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
