package AtCoder.ABC.ABC246.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        if (n == 0) {
            System.out.println(0);
            return;
        }
        final long answer = LongStream.rangeClosed(1, 1_000_000)
            .map(a -> {
                final long b = binarySearch(-1, 1_000_000, a, n);
                return calcProd(a, b);
            }).min()
            .orElseThrow();
        System.out.println(answer);
    }

    private static long binarySearch(final long begin, final long end, final long a, final long n) {
        if (end - begin <= 1) {
            return end;
        }

        final long b = (begin + end) / 2;
        final long prod = calcProd(a, b);
        if (prod == n) {
            return b;
        } else if (prod > n) {
            return binarySearch(begin, b, a, n);
        } else {
            return binarySearch(b, end, a, n);
        }
    }

    private static long calcProd(final long a, final long b) {
        return (a * a * a) + (a * a * b) + (a * b * b) + (b * b * b);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
