package AtCoder.ARC.ARC128.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            int min = Integer.MAX_VALUE;

            if (a % 3 == b % 3) {
                min = Math.max(a, b);
            }
            if (b % 3 == c % 3) {
                min = Math.min(min, Math.max(b, c));
            }
            if (c % 3 == a % 3) {
                min = Math.min(min, Math.max(c, a));
            }

            if (min == Integer.MAX_VALUE) {
                joiner.add("-1");
            } else {
                joiner.add(Integer.toString(min));
            }
        }
        System.out.println(joiner);
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
