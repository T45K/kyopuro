package ABC.ABC197.B;

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
        final int x = scanner.nextInt() - 1;
        final int y = scanner.nextInt() - 1;
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = s.charAt(j) == '.';
            }
        }

        int count = 1;
        for (int i = x - 1; i >= 0 && table[i][y]; i--) {
            count++;
        }
        for (int i = x + 1; i < h && table[i][y]; i++) {
            count++;
        }
        for (int j = y - 1; j >= 0 && table[x][j]; j--) {
            count++;
        }
        for (int j = y + 1; j < w && table[x][j]; j++) {
            count++;
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
