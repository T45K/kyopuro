package ABC.ABC175.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long x = abs(scanner.nextLong());
        final long k = abs(scanner.nextLong());
        final long d = abs(scanner.nextLong());

        if (x / d >= k) {
            System.out.println(x - d * k);
            return;
        }

        final long k1 = x / d;
        if (k1 % 2 == k % 2) {
            System.out.println(x - d * k1);
        } else {
            System.out.println(-(x - d * (k1 + 1)));
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
    