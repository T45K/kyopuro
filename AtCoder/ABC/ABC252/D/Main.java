package AtCoder.ABC.ABC252.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, Long> counts = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final long[] values = counts.entrySet().stream()
            .sorted(Comparator.comparingInt(Map.Entry::getKey))
            .map(Map.Entry::getValue)
            .mapToLong(Long::longValue)
            .toArray();
        final int m = values.length;
        final long[] firstAccumulation = new long[m];
        firstAccumulation[0] = values[0];
        for (int i = 1; i < m; i++) {
            firstAccumulation[i] = firstAccumulation[i - 1] + values[i];
        }
        final long[] secondAccumulation = new long[m];
        secondAccumulation[0] = firstAccumulation[0];
        for (int i = 1; i < m; i++) {
            secondAccumulation[i] = secondAccumulation[i - 1] + firstAccumulation[i];
        }
        final long[] product = new long[m];
        product[1] = values[0] * values[1];
        for (int i = 2; i < m; i++) {
            product[i] = secondAccumulation[i - 1] * values[i];
        }

        long sum = 0;
        for (int i = m - 1; i >= 2; i--) {
            sum += values[i] * product[i - 1];
        }
        System.out.println(sum);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
