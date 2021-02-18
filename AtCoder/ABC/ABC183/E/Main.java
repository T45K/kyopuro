package AtCoder.ABC.ABC183.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
理想としてはセル毎に全方向から累積
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String line = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = line.charAt(j) == '.';
            }
        }

        final long[][][] count = new long[h][w][4]; // 単体，左上，上，左からの累積
        count[0][0][0] = 1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!table[i][j]) {
                    continue;
                }
                if (i != 0 && j != 0) { // 左上
                    count[i][j][0] += count[i - 1][j - 1][0] + count[i - 1][j - 1][1];
                    count[i][j][1] = (count[i - 1][j - 1][0] + count[i - 1][j - 1][1]) % MOD;
                }
                if (i != 0) { // 上
                    count[i][j][0] += count[i - 1][j][0] + count[i - 1][j][2];
                    count[i][j][2] = (count[i - 1][j][0] + count[i - 1][j][2]) % MOD;
                }
                if (j != 0) { // 左
                    count[i][j][0] += count[i][j - 1][0] + count[i][j - 1][3];
                    count[i][j][3] = (count[i][j - 1][0] + count[i][j - 1][3]) % MOD;
                }
                count[i][j][0] %= MOD;
            }
        }
        System.out.println(count[h - 1][w - 1][0]);
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
