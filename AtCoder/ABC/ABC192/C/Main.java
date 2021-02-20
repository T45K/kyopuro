package AtCoder.ABC.ABC192.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
やるだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        String x = Integer.toString(n);
        for (int i = 0; i < k; i++) {
            final char[] xArray = x.toCharArray();
            Arrays.sort(xArray);
            x = new String(xArray);
            final int g1 = Integer.parseInt(new StringBuilder(x).reverse().toString());
            final int g2 = Integer.parseInt(x);
            x = Integer.toString(g1 - g2);
        }
        System.out.println(x);
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
