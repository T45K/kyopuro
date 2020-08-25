package AtCoder.AGC.AGC046.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
DP
ある行に一つだけ黒があれば，その黒は行が加えられたタイミングで塗られる
二つ以上あれば，列が加えられたタイミングで塗られるとする
上で列が加えられた場合，黒く塗られるのは最新行にする
 */
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final int d = scanner.nextInt();

        if (a == c && b == d) {
            System.out.println(1);
            return;
        }

        final long[][] addedCol = new long[c + 1][d + 1];
        addedCol[a][b] = 1;
        for (int i = a + 1; i <= c; i++) {
            addedCol[i][b] = addedCol[i - 1][b] * b % MOD;
        }

        final long[][] addedRow = new long[c + 1][d + 1];
        addedRow[a][b] = 1;
        for (int i = b + 1; i <= d; i++) {
            addedRow[a][i] = addedRow[a][i - 1] * a % MOD;
        }

        for (int i = a + 1; i <= c; i++) {
            for (int j = b + 1; j <= d; j++) {
                addedCol[i][j] = (addedCol[i - 1][j] + addedRow[i - 1][j]) * j % MOD;
                addedRow[i][j] = (addedCol[i][j - 1] + addedRow[i][j - 1] * i) % MOD;
            }
        }

        System.out.println((addedCol[c][d] + addedRow[c][d]) % MOD);
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
    