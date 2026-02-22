package ABC.ABC191.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
頂点数を調べるよりも辺の数を調べた方が個人的には楽
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = s.charAt(j) == '#';
            }
        }
        int count = 0;
        for (int i = 1; i < h; i++) {
            boolean isContiguous = false;
            for (int j = 1; j < w; j++) {
                if (table[i][j] && table[i - 1][j] || !table[i][j] && !table[i - 1][j]) {
                    isContiguous = false;
                    continue;
                }
                if (!isContiguous) {
                    count++;
                    isContiguous = true;
                }
            }
        }
        for (int j = 1; j < w; j++) {
            boolean isContiguous = false;
            for (int i = 1; i < h; i++) {
                if (table[i][j] && table[i][j - 1] || !table[i][j] && !table[i][j - 1]) {
                    isContiguous = false;
                    continue;
                }
                if (!isContiguous) {
                    count++;
                    isContiguous = true;
                }
            }
        }
        System.out.println(count);
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
    