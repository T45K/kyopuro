package ABC107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D {

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int q = scanner.nextInt();

        final int[][] table = new int[n][n];

        for (int i = 0; i < m; i++) {
            final int begin = scanner.nextInt() - 1;
            final int end = scanner.nextInt() - 1;
            table[begin][end]++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                table[i][j] += table[i][j - 1];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] += table[i - 1][j];
            }
        }

        for (int i = 0; i < q; i++) {
            final int begin = scanner.nextInt() - 1;
            final int end = scanner.nextInt() - 1;

            int tmp = 0;
            if (begin != 0) {
                tmp = -table[begin - 1][begin - 1];
                tmp += table[begin - 1][end];
                tmp += table[end][begin - 1];
            }

            System.out.println(table[end][end] - tmp);
        }
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
