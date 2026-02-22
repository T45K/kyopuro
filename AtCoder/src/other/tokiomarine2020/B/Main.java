package other.tokiomarine2020.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long a = scanner.nextInt();
        final long v = scanner.nextInt();
        final long b = scanner.nextInt();
        final long w = scanner.nextInt();
        final long t = scanner.nextInt();

        if (a < b) {
            if (a + v * t >= b + w * t) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            if (a - v * t <= b - w * t) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
