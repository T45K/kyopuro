package AtCoder.ABC.ABC210.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final long c = scanner.nextLong();
        final long[][] table = new long[h + 1][w + 1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        long max = Long.MAX_VALUE;
        final long[][] calc1 = new long[h + 1][w + 1]; // 左上 -> 右下
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                if (i == 1 && j == 1) {
                    calc1[i][j] = 2 * table[i][j];
                    continue;
                }

                if (i == 1) {
                    calc1[i][j] = table[i][j] + Math.min(table[i][j - 1] + c, calc1[i][j - 1] - table[i][j - 1] + c);
                    max = Math.min(max, calc1[i][j]);
                    continue;
                }

                if (j == 1) {
                    calc1[i][j] = table[i][j] + Math.min(table[i - 1][j] + c, calc1[i - 1][j] - table[i - 1][j] + c);
                    max = Math.min(max, calc1[i][j]);
                    continue;
                }

                calc1[i][j] = table[i][j] + Math.min(Math.min(table[i][j - 1] + c, calc1[i][j - 1] - table[i][j - 1] + c), Math.min(table[i - 1][j] + c, calc1[i - 1][j] - table[i - 1][j] + c));
                max = Math.min(max, calc1[i][j]);
            }
        }

        final long[][] calc2 = new long[h + 1][w + 1]; // 右上 -> 左下
        for (int i = 1; i <= h; i++) {
            for (int j = w; j > 0; j--) {
                if (i == 1 && j == w) {
                    calc2[i][j] = 2 * table[i][j];
                    continue;
                }

                if (i == 1) {
                    calc2[i][j] = table[i][j] + Math.min(table[i][j + 1] + c, calc2[i][j + 1] - table[i][j + 1] + c);
                    max = Math.min(max, calc2[i][j]);
                    continue;
                }

                if (j == w) {
                    calc2[i][j] = table[i][j] + Math.min(table[i - 1][j] + c, calc2[i - 1][j] - table[i - 1][j] + c);
                    max = Math.min(max, calc2[i][j]);
                    continue;
                }

                calc2[i][j] = table[i][j] + Math.min(Math.min(table[i][j + 1] + c, calc2[i][j + 1] - table[i][j + 1] + c), Math.min(table[i - 1][j] + c, calc2[i - 1][j] - table[i - 1][j] + c));
                max = Math.min(max, calc2[i][j]);
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
