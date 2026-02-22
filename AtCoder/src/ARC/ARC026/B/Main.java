package ARC.ARC026.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final long n = Stream.of(System.in)
            .map(FastScanner::new)
            .mapToLong(FastScanner::nextLong)
            .findFirst()
            .getAsLong();

        if (n == 1) {
            System.out.println("Deficient");
            return;
        }

        Stream.of(n)
            .map(Main::primeFactorization)
            .map(Map::entrySet)
            .flatMap(Collection::stream)
            .map(entry -> LongStream.rangeClosed(0, entry.getValue())
                .map(i -> iterativePow(entry.getKey(), i))
                .sum()
            )
            .reduce((a, b) -> a * b)
            .ifPresent(answer -> {
                if (answer == n * 2) {
                    System.out.println("Perfect");
                } else if (answer < n * 2) {
                    System.out.println("Deficient");
                } else {
                    System.out.println("Abundant");
                }
            });
    }

    private static long iterativePow(long a, long b) {
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
            }
            a *= a;
            b >>= 1;
        }

        return tmp;
    }

    private static Map<Long, Long> primeFactorization(long n) {
        final double sqrt = Math.sqrt(n);
        final Map<Long, Long> countMap = new HashMap<>();
        for (long i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                countMap.compute(i, (k, v) -> v = v == null ? 1 : v + 1);
                n /= i;
                i--;
            }
        }

        if (n != 1) {
            countMap.compute(n, (k, v) -> v = v == null ? 1 : v + 1);
        }

        return countMap;
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
