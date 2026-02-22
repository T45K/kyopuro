package ABC.ABC168.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final double a = scanner.nextDouble();
        final double b = scanner.nextDouble();
        final double h = scanner.nextDouble();
        final double m = scanner.nextDouble();

        final double hPi = 2 * Math.PI * ((h + m / 60d) / 12d);
        final double mPi = Math.PI * m / 30d;

        System.out.println(Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(hPi - mPi)));
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
