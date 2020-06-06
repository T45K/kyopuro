package AtCoder.other.past_3.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final boolean[][] table = new boolean[5][4 * n + 2];
        for (int i = 0; i < 5; i++) {
            final String s = scanner.next();
            for (int j = 0; j < 4 * n + 1; j++) {
                table[i][j] = s.charAt(j) == '#';
            }
        }

        final StringBuilder builder = new StringBuilder();
        for (int i = 1; i * 4 < 4 * n + 2; i++) {
            builder.append(convert(table, i * 4 - 1));
        }
        System.out.println(builder);
    }

    private static int convert(final boolean[][] table, final int j) {
        if (table[0][j - 2] && table[0][j - 1] && table[0][j]
            && table[1][j - 2] && !table[1][j - 1] && table[1][j]
            && table[2][j - 2] && !table[2][j - 1] && table[2][j]
            && table[3][j - 2] && !table[3][j - 1] && table[3][j]
            && table[4][j - 2] && table[4][j - 1] && table[4][j]) {
            return 0;
        }

        if (!table[0][j - 2] && table[0][j - 1] && !table[0][j]
            && table[1][j - 2] && table[1][j - 1] && !table[1][j]
            && !table[2][j - 2] && table[2][j - 1] && !table[2][j]
            && !table[3][j - 2] && table[3][j - 1] && !table[3][j]
            && table[4][j - 2] && table[4][j - 1] && table[4][j]) {
            return 1;
        }

        if (table[0][j - 2] && table[0][j - 1] && table[0][j]
            && !table[1][j - 2] && !table[1][j - 1] && table[1][j]
            && table[2][j - 2] && table[2][j - 1] && table[2][j]
            && table[3][j - 2] && !table[3][j - 1] && !table[3][j]
            && table[4][j - 2] && table[4][j - 1] && table[4][j]) {
            return 2;
        }

        if (table[0][j - 2] && table[0][j - 1] && table[0][j]
            && !table[1][j - 2] && !table[1][j - 1] && table[1][j]
            && table[2][j - 2] && table[2][j - 1] && table[2][j]
            && !table[3][j - 2] && !table[3][j - 1] && table[3][j]
            && table[4][j - 2] && table[4][j - 1] && table[4][j]) {
            return 3;
        }

        if (table[0][j - 2] && !table[0][j - 1] && table[0][j]
            && table[1][j - 2] && !table[1][j - 1] && table[1][j]
            && table[2][j - 2] && table[2][j - 1] && table[2][j]
            && !table[3][j - 2] && !table[3][j - 1] && table[3][j]
            && !table[4][j - 2] && !table[4][j - 1] && table[4][j]) {
            return 4;
        }

        if (table[0][j - 2] && table[0][j - 1] && table[0][j]
            && table[1][j - 2] && !table[1][j - 1] && !table[1][j]
            && table[2][j - 2] && table[2][j - 1] && table[2][j]
            && !table[3][j - 2] && !table[3][j - 1] && table[3][j]
            && table[4][j - 2] && table[4][j - 1] && table[4][j]) {
            return 5;
        }

        if (table[0][j - 2] && table[0][j - 1] && table[0][j]
            && table[1][j - 2] && !table[1][j - 1] && !table[1][j]
            && table[2][j - 2] && table[2][j - 1] && table[2][j]
            && table[3][j - 2] && !table[3][j - 1] && table[3][j]
            && table[4][j - 2] && table[4][j - 1] && table[4][j]) {
            return 6;
        }

        if (table[0][j - 2] && table[0][j - 1] && table[0][j]
            && !table[1][j - 2] && !table[1][j - 1] && table[1][j]
            && !table[2][j - 2] && !table[2][j - 1] && table[2][j]
            && !table[3][j - 2] && !table[3][j - 1] && table[3][j]
            && !table[4][j - 2] && !table[4][j - 1] && table[4][j]) {
            return 7;
        }

        if (table[0][j - 2] && table[0][j - 1] && table[0][j]
            && table[1][j - 2] && !table[1][j - 1] && table[1][j]
            && table[2][j - 2] && table[2][j - 1] && table[2][j]
            && table[3][j - 2] && !table[3][j - 1] && table[3][j]
            && table[4][j - 2] && table[4][j - 1] && table[4][j]) {
            return 8;
        }

        return 9;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
