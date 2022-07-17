package AtCoder.ABC.ABC260.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();

        final long[] reds = new long[11];
        final long[] blues = new long[11];
        reds[n] = 1;

        while (Arrays.stream(reds, 2, 11).anyMatch(v -> v > 0)
            || Arrays.stream(blues, 2, 11).anyMatch(v -> v > 0)) {
            for (int i = 10; i >= 2; i--) {
                reds[i - 1] += reds[i];
                blues[i] += x * reds[i];
                reds[i] = 0;
            }

            for (int i = 10; i >= 2; i--) {
                reds[i - 1] += blues[i];
                blues[i - 1] += y * blues[i];
                blues[i] = 0;
            }
        }

        System.out.println(blues[1]);
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
