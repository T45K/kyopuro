package AtCoder.ABC.ABC220.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = Stream.generate(scanner::nextLong)
            .limit(n)
            .mapToLong(Long::longValue)
            .toArray();
        final long x = scanner.nextLong();

        final long sum = Arrays.stream(array).sum();
        final long loop = x / sum;
        final long rest = x - sum * loop;
        final int found = find(array, rest);

        System.out.println(n * loop + found);
    }

    private static int find(final long[] array, final long value) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (sum > value) {
                return i;
            }
            sum += array[i];
        }
        return array.length;
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
