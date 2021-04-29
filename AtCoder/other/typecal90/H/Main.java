package AtCoder.other.typecal90.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) throws IOException {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final long[][] table = new long[n][7];
        final char[] array = "atcoder".toCharArray();
        if (s.charAt(0) == 'a') {
            table[0][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            table[i][0] = table[i - 1][0] + ((s.charAt(i) == array[0]) ? 1 : 0);
            table[i][0] %= MOD;
            for (int j = 1; j < array.length; j++) {
                if (s.charAt(i) == array[j]) {
                    table[i][j] = table[i - 1][j - 1];
                }
                table[i][j] += table[i - 1][j];
                table[i][j] %= MOD;
            }
        }
        System.out.println(table[n - 1][6]);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = new StringTokenizer(reader.readLine());
        }

        String next() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
    