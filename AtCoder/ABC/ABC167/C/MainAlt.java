package AtCoder.ABC.ABC167.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainAlt {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int x = scanner.nextInt();

        final int[][] books = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            books[i][0] = scanner.nextInt();
            for (int j = 1; j <= m; j++) {
                books[i][j] = scanner.nextInt();
            }
        }

        int min = Integer.MAX_VALUE;
        for (int bit = 1; bit < 1 << n; bit++) {
            final int[] sum = new int[m + 1];
            int price = 0;
            for (int i = 1; i <= n; i++) {
                if ((bit & 1 << (i - 1)) > 0) {
                    for (int j = 1; j <= m; j++) {
                        sum[j] += books[i][j];
                    }
                    price += books[i][0];
                }
            }

            final boolean allMatch = Arrays.stream(sum, 1, m + 1).allMatch(v -> v >= x);
            if (allMatch) {
                min = Math.min(min, price);
            }
        }

        System.out.println(min < Integer.MAX_VALUE ? min : -1);
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
