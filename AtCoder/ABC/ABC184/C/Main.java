package AtCoder.ABC.ABC184.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int r1 = scanner.nextInt();
        final int c1 = scanner.nextInt();
        final int r2 = scanner.nextInt();
        final int c2 = scanner.nextInt();
        final int r = Math.abs(r1 - r2);
        final int c = Math.abs(c1 - c2);
        if (r == 0 && c == 0) {
            System.out.println(0);
        } else if (r == c || r + c <= 3) {
            System.out.println(1);
        } else if (Math.abs(r - c) <= 3 || Math.abs(r - c) % 2 == 0 || r + c <= 6) {
            System.out.println(2);
        } else {
            System.out.println(3);
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
