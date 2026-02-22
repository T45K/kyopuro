package ABC.ABC171.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = new long[100_001];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            array[a]++;
            sum += a;
        }

        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            final long tmp = array[b];
            array[b] = 0;
            array[c] += tmp;
            sum = sum + tmp * (c - b);
            System.out.println(sum);
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
