package AtCoder.ABC.ABC183.D;

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
        final int w = scanner.nextInt();
        final long[] array = new long[2 * 100_000 + 1];
        for (int i = 0; i < n; i++) {
            final int s = scanner.nextInt();
            final int t = scanner.nextInt();
            final int p = scanner.nextInt();
            array[s] += p;
            array[t] -= p;
        }

        for (int i = 1; i < array.length; i++) {
            array[i] += array[i - 1];
        }

        final boolean answer = Arrays.stream(array)
            .allMatch(v -> v <= w);
        System.out.println(answer ? "Yes" : "No");
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
