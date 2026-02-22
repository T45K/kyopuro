package other.typecal90.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final double t = scanner.nextInt();
        final double l = scanner.nextInt();
        final double x = scanner.nextInt();
        final double y = scanner.nextInt();
        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            final double e = scanner.nextInt();
            final double sin = Math.sin(2 * Math.PI * e / t);
            final double cos = Math.cos(2 * Math.PI * e / t);
            final double height = (-cos + 1) / 2 * l;
            final double distance = Math.sqrt(x * x + (y - sin / 2 * l) * (y - sin / 2 * l));
            final double answer = Math.toDegrees(Math.atan2(height, distance));
            System.out.println(answer);
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
