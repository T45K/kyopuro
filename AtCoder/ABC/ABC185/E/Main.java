package AtCoder.ABC.ABC185.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
A_0とB_0の文字列から，列を伸ばしていくイメージ
Aだけを伸ばす -> 伸ばした分を消すのにコスト1
Bだけを伸ばす -> 同上
AとBを伸ばす -> AとBが同一ならコスト0，違うなら差異箇所とみなすためコスト1
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }
        final int[] b = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            b[i] = scanner.nextInt();
        }
        final int[][] table = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            table[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            table[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                final int min = Math.min(table[i - 1][j], table[i][j - 1]) + 1;
                table[i][j] = Math.min(min, table[i - 1][j - 1] + (a[i] == b[j] ? 0 : 1));
            }
        }
        System.out.println(table[n][m]);
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
