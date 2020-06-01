package AtCoder.AGC.AGC005.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String x = scanner.next();
        int s = 0;
        int t = 0;
        for (int i = 0; i < x.length(); i++) {
            final char c = x.charAt(i);
            if (c == 'S') {
                s++;
            } else if (s > 0) {
                t++;
                s--;
            }
        }
        System.out.println(x.length() - 2 * t);
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
    }
}
    