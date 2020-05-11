package AtCoder.other.cf_2015_morning_easy.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
文字列を2つに分割してLCSするだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        int max = -1;
        for (int i = 0; i < n; i++) {
            final char current = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                final char target = s.charAt(j);
                if (current != target) {
                    continue;
                }

                max = Math.max(max, lcs(s.substring(i, j), s.substring(j)));
            }
        }

        if (max == -1) {
            System.out.println(n);
        } else {
            System.out.println(n - 2 * max);
        }
    }

    private static int lcs(final String s, final String t) {
        final int[][] table = new int[s.length()][t.length()];
        for (int j = 0; j < t.length(); j++) {
            table[0][j] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            table[i][0] = 1;
            for (int j = 1; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }

        return table[s.length() - 1][t.length() - 1];
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
    