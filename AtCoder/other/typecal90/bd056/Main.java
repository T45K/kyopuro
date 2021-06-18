package AtCoder.other.typecal90.bd056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
DPするだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int s = scanner.nextInt();
        final String[][] table = new String[n][100_001];
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            if (i == 0) {
                table[0][a] = "A";
                table[0][b] = "B";
                continue;
            }
            for (int j = 0; j <= 100_000 - a; j++) {
                if (table[i - 1][j] != null && table[i][j + a] == null) {
                    table[i][j + a] = table[i - 1][j] + "A";
                }
            }
            for (int j = 0; j <= 100_000 - b; j++) {
                if (table[i - 1][j] != null && table[i][j + b] == null) {
                    table[i][j + b] = table[i - 1][j] + "B";
                }
            }
        }

        if (table[n - 1][s] != null) {
            System.out.println(table[n - 1][s]);
        } else {
            System.out.println("Impossible");
        }
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
