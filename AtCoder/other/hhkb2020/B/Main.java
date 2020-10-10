package AtCoder.other.hhkb2020.B;

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
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final char[] array = scanner.next().toCharArray();
            for (int j = 0; j < w; j++) {
                table[i][j] = array[j] == '.';
            }
        }

        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!table[i][j]) {
                    continue;
                }
                if (i < h - 1 && table[i + 1][j]) {
                    count++;
                }
                if (j < w - 1 && table[i][j + 1]) {
                    count++;
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
