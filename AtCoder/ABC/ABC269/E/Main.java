package AtCoder.ABC.ABC269.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        int a = 1;
        int b = n;
        int c = 1;
        int d = n;

        while (a < b) {
            final int mid = (a + b) / 2;
            System.out.printf("? %d %d %d %d", a, mid, 1, n);
            System.out.println();

            final int count = scanner.nextInt();
            if (count < mid - a + 1) {
                b = mid;
            } else {
                a = mid + 1;
            }
        }

        while (c < d) {
            final int mid = (c + d) / 2;
            System.out.printf("? %d %d %d %d", 1, n, c, mid);
            System.out.println();

            final int count = scanner.nextInt();
            if (count < mid - c + 1) {
                d = mid;
            } else {
                c = mid + 1;
            }
        }

        System.out.printf("! %d %d", a, c);
        System.out.println();
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
