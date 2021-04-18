package AtCoder.ABC.ABC130.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
DP
1. 繋げ（られ）る: その文字以前の全ての共通部分列から伸ばせるので、dp[i-1][0~j-1]の総和+1
2. 繋げない: i-1の結果と同じ
1と2の和になる
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] s = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final int[] t = Stream.generate(scanner::nextInt)
            .limit(m)
            .mapToInt(Integer::intValue)
            .toArray();
        final long[][] dp = new long[n][m];
        for (int i = 0; i < m; i++) {
            dp[0][i] = s[0] == t[i] ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = s[i] == t[0] ? 1 : 0;
            long sum = dp[i - 1][0];
            for (int j = 1; j < m; j++) {
                if (s[i] == t[j]) {
                    dp[i][j] = (sum + 1) % MOD;
                }
                sum += dp[i - 1][j];
                sum %= MOD;
            }
            for (int j = 0; j < m; j++) {
                dp[i][j] += dp[i - 1][j];
                dp[i][j] %= MOD;
            }
        }
        final long answer = Arrays.stream(dp[n - 1])
            .reduce(0, (a, b) -> (a + b) % MOD) + 1;
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
