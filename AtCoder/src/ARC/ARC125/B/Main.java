package ARC.ARC125.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final long root = (long) Math.sqrt(n);
        final long answer = LongStream.rangeClosed(1, root)
            .map(value -> value % 2 == 0
                ? countEven(n / value) - countEven(value - 1)
                : countOdd(n / value) - countOdd(value - 1))
            .reduce((a, b) -> (a + b) % MOD)
            .orElse(0);
        System.out.println(answer);
    }

    private static long countOdd(final long value) {
        return (value + 1) / 2;
    }

    private static long countEven(final long value) {
        return value / 2;
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
