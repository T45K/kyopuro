package ABC.ABC205.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();
        final long c = scanner.nextLong();

        final boolean isEven = c % 2 == 0;
        if (isEven) {
            if (Math.abs(a) == Math.abs(b)) {
                System.out.println("=");
            } else if (Math.abs(a) > Math.abs(b)) {
                System.out.println(">");
            } else {
                System.out.println("<");
            }
        } else {
            if (a == b) {
                System.out.println("=");
            } else if (a > b) {
                System.out.println(">");
            } else {
                System.out.println("<");
            }
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
