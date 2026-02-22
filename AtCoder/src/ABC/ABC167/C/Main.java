package ABC.ABC167.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
全探索するだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int x = scanner.nextInt();

        final long[][] table = new long[n][m];
        final int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
            for (int j = 0; j < m; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final long[] sum = new long[m];
        final int answer = permutation(0, prices, 0, sum, x, table);
        System.out.println(answer < Integer.MAX_VALUE ? answer : -1);
    }

    private static int permutation(final int current, final int[] prices, final int price, final long[] sum, final int x, final long[][] table) {
        if (current == table.length) {
            return Integer.MAX_VALUE;
        }

        for (int i = 0; i < sum.length; i++) {
            sum[i] += table[current][i];
        }

        final boolean allOver = Arrays.stream(sum)
                .allMatch(l -> l >= x);
        int min = allOver ? price + prices[current] : Integer.MAX_VALUE;

        min = Math.min(min, permutation(current + 1, prices, price + prices[current], sum, x, table));

        for (int i = 0; i < sum.length; i++) {
            sum[i] -= table[current][i];
        }

        return Math.min(min, permutation(current + 1, prices, price, sum, x, table));
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
