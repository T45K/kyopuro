package AtCoder.ABC.ABC169.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int s = scanner.nextInt();
        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        final long[][] table = new long[n + 1][s + 1];
        table[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= s; j++) {
                table[i][j] = table[i - 1][j] * 2;
                table[i][j] %= MOD;
            }
            final int current = array[i];
            for (int j = 0; j <= s - current; j++) {
                table[i][j + current] += table[i - 1][j];
                table[i][j + current] %= MOD;
            }
        }

        System.out.println(table[n][s]);
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
