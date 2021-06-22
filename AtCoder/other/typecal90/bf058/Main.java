package AtCoder.other.typecal90.bf058;

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
        final long k = scanner.nextLong();
        final int[] array = new int[100_000];
        Arrays.fill(array, -1);
        array[n] = 0;
        int current = n;
        for (int i = 1; ; i++) {
            current = calc(current);
            if (i == k) {
                System.out.println(current);
                return;
            }
            if (array[current] == -1) {
                array[current] = i;
                continue;
            }
            final int loop = i - array[current];
            final long rest = (k - i) % loop;
            for (long j = 0; j < rest; j++) {
                current = calc(current);
            }
            System.out.println(current);
            return;
        }
    }

    private static int calc(final int value) {
        return (value + value % 10 + value / 10 % 10 + value / 100 % 10 + value / 1000 % 10 + value / 10000 % 10) % 100_000;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
