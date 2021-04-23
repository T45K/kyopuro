package AtCoder.other.typecal90.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int l = scanner.nextInt();
        final int k = scanner.nextInt();
        final long[] array = new long[n + 1];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        array[n] = l;
        for (int i = n; i > 0; i--) {
            array[i] -= array[i - 1];
        }
        final long answer = binarySearch(1, 1_000_000_000_000_000_000L, array, k);
        System.out.println(answer);
    }

    private static long binarySearch(final long begin, final long end, final long[] array, final int k) {
        if (end - begin <= 1) {
            return begin;
        }
        final long mid = (begin + end) / 2;
        final int count = count(mid, array);
        if (count >= k + 1) {
            return binarySearch(mid, end, array, k);
        } else {
            return binarySearch(begin, mid, array, k);
        }
    }

    private static int count(final long max, final long[] array) {
        int count = 0;
        long current = 0;
        for (final long length : array) {
            current += length;
            if (current >= max) {
                count++;
                current = 0;
            }
        }
        return count;
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
