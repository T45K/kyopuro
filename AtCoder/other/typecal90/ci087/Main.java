package AtCoder.other.typecal90.ci087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int p = scanner.nextInt();
        final int k = scanner.nextInt();
        final long[][] table = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                final int a = scanner.nextInt();
                table[i][j] = a;
            }
        }

        final Supplier<long[][]> createTableCopy = () -> {
            final long[][] dest = new long[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                System.arraycopy(table[i], 1, dest[i], 1, n);
            }
            return dest;
        };

        final Function<Long, Long> countPairsWhoseDistanceIsLessThanOrEqualP = x -> {
            final long[][] copy = createTableCopy.get();
            replaceMinusToGivenValue(copy, x);
            warshallFloyd(copy, n);

            return IntStream.range(1, n)
                .boxed()
                .flatMapToLong(i -> IntStream.rangeClosed(i + 1, n)
                    .mapToLong(j -> copy[i][j])
                ).filter(v -> v <= p)
                .count();
        };

        final BinarySearch binarySearch = new BinarySearch() {
            @Override
            public long exec(final long begin, final long end, final Function<Long, Boolean> judge) {
                if (end - begin <= 1) {
                    return end;
                }

                final long mid = (begin + end) / 2;
                final long count = countPairsWhoseDistanceIsLessThanOrEqualP.apply(mid);
                if (judge.apply(count)) {
                    return this.exec(begin, mid, judge);
                } else {
                    return this.exec(mid, end, judge);
                }
            }
        };

        final long lowerBound = binarySearch.exec(0, Long.MAX_VALUE / 2, count -> count <= k);
        final long upperBound = binarySearch.exec(0, Long.MAX_VALUE / 2, count -> count < k);

        if (lowerBound < Long.MAX_VALUE / 2 && upperBound == Long.MAX_VALUE / 2) {
            System.out.println("Infinity");
        } else {
            System.out.println(upperBound - lowerBound);
        }
    }

    private static void replaceMinusToGivenValue(final long[][] table, final long given) {
        for (final long[] array : table) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == -1) {
                    array[i] = given;
                }
            }
        }
    }

    private static void warshallFloyd(final long[][] table, final int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }
    }

    @FunctionalInterface
    private interface BinarySearch {
        long exec(final long begin, final long end, final Function<Long, Boolean> judge);
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
