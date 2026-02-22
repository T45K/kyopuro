package other.typecal90.AP042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int k = scanner.nextInt();
        if (k % 9 != 0) {
            System.out.println(0);
            return;
        }

        final long[] array = new long[k + 2];
        array[1] = 1;
        for (int i = 1; i < k + 1; i++) {
            for (int j = 1; j <= 9 && i + j < k + 2; j++) {
                array[i + j] += array[i];
                array[i + j] %= MOD;
            }
        }

        System.out.println(array[k + 1]);
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
