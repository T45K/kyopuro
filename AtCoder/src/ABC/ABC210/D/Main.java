package ABC.ABC210.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
左から右に行くことを考えると、
dp[i]の値は
- 左隣から直接線路を引く
- 左隣で最小の費用を考慮して線路を引く
の最小値になる
後者に関しては、左隣での費用はどこかから左隣までの線路を引いた時の値になっているので、
左隣の地価を引いて今見ている土地の地価とCを足せば良い
左隣での値 - A[i-1] + A[i+1] + C
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final long c = scanner.nextLong();
        final long[][] table = new long[h + 1][w + 2];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        long max = Long.MAX_VALUE;
        final long[][] leftToRightDP = new long[h + 1][w + 1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                if (i == 1 && j == 1) {
                    leftToRightDP[i][j] = 2 * table[i][j];
                    continue;
                }

                final long fromLeft = Math.min(table[i][j - 1] + c, leftToRightDP[i][j - 1] - table[i][j - 1] + c);
                final long fromAbove = Math.min(table[i - 1][j] + c, leftToRightDP[i - 1][j] - table[i - 1][j] + c);
                if (i == 1) {
                    leftToRightDP[i][j] = table[i][j] + fromLeft;
                } else if (j == 1) {
                    leftToRightDP[i][j] = table[i][j] + fromAbove;
                } else {
                    leftToRightDP[i][j] = table[i][j] + Math.min(fromLeft, fromAbove);
                }

                max = Math.min(max, leftToRightDP[i][j]);
            }
        }

        final long[][] rightToLeftDP = new long[h + 1][w + 2];
        for (int i = 1; i <= h; i++) {
            for (int j = w; j > 0; j--) {
                if (i == 1 && j == w) {
                    rightToLeftDP[i][j] = 2 * table[i][j];
                    continue;
                }

                final long fromRight = Math.min(table[i][j + 1] + c, rightToLeftDP[i][j + 1] - table[i][j + 1] + c);
                final long fromAbove = Math.min(table[i - 1][j] + c, rightToLeftDP[i - 1][j] - table[i - 1][j] + c);
                if (i == 1) {
                    rightToLeftDP[i][j] = table[i][j] + fromRight;
                } else if (j == w) {
                    rightToLeftDP[i][j] = table[i][j] + fromAbove;
                } else {
                    rightToLeftDP[i][j] = table[i][j] + Math.min(fromRight, fromAbove);
                }

                max = Math.min(max, rightToLeftDP[i][j]);
            }
        }
        System.out.println(max);
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
