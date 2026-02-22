package ABC.ABC303.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final String t = scanner.next();
        final boolean isSimilar = IntStream.range(0, n)
            .allMatch(i -> isSimilarChar(s.charAt(i), t.charAt(i)));
        System.out.println(isSimilar ? "Yes" : "No");
    }

    private static boolean isSimilarChar(final char x, final char y) {
        return x == y ||
            x == '1' && y == 'l' || x == 'l' && y == '1' ||
            x == '0' && y == 'o' || x == 'o' && y == '0';
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
