package AtCoder.ABC.ABC300.B;

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
        final char[][] a = new char[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            a[i] = s.toCharArray();
        }
        final char[][] b = new char[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            b[i] = s.toCharArray();
        }
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                if (match(a, b, h, w, x, y)) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    private static boolean match(final char[][] a, final char[][] b, final int h, final int w, final int x, final int y) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (a[(i + x) % h][(j + y) % w] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
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
