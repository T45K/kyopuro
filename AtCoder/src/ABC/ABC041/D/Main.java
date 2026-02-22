package ABC.ABC041.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
巡回セールスマン問題の応用
想定解はトポロジカルソートらしい
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] faster = new int[n];
        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt() - 1;
            final int y = scanner.nextInt() - 1;
            faster[y] |= 1 << x;
        }
        final long[][] dp = new long[1 << n][n];
        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 1;
        }
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & 1 << j) == 0) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    final int next = i | 1 << k;
                    if (next == i || (faster[k] & i) > 0) { // 目撃情報と矛盾する
                        continue;
                    }
                    dp[next][k] += dp[i][j];
                }
            }
        }
        final long sum = Arrays.stream(dp[(1 << n) - 1]).sum();
        System.out.println(sum);
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
