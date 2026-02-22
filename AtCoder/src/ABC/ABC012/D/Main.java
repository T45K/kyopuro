package ABC.ABC012.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[][] table = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(table[i], Integer.MAX_VALUE / 2);
            table[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int t = scanner.nextInt();

            table[a][b] = t;
            table[b][a] = t;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }

        IntStream.rangeClosed(1, n)
            .map(i -> IntStream.rangeClosed(1, n)
                .map(j -> table[i][j])
                .max()
                .orElse(Integer.MAX_VALUE)
            ).min()
            .ifPresent(System.out::println);
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
