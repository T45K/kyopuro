package AtCoder.ABC.ABC194.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] original = new long[n];
        for (int i = 0; i < n; i++) {
            original[i] = scanner.nextInt();
        }
        final long[] pow = new long[n];
        pow[n - 1] = original[n - 1] * original[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            pow[i] = original[i] * original[i] + pow[i + 1];
        }
        final long[] acc = new long[n];
        acc[n - 1] = original[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            acc[i] = original[i] + acc[i + 1];
        }
        long sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += (n - i - 1) * original[i] * original[i] + pow[i + 1] - 2 * original[i] * acc[i + 1];
        }
        System.out.println(sum);
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
