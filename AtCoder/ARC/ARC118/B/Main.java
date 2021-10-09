package AtCoder.ARC.ARC118.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int k = scanner.nextInt();
        final long n = scanner.nextInt();
        final long m = scanner.nextInt();
        final List<Integer> aList = Stream.generate(scanner::nextInt)
            .limit(k)
            .collect(Collectors.toList());
        final List<Long> bList = aList.stream()
            .map(a -> a * m / n)
            .collect(Collectors.toList());
        final long sum = bList.stream()
            .mapToLong(Long::longValue)
            .sum();
        final Set<Integer> set = IntStream.range(0, k)
            .mapToObj(i -> {
                final long b = bList.get(i);
                final long a = aList.get(i);
                return new Pair<>(i, Math.abs(n * (b + 1) - m * a));
            })
            .sorted(Comparator.comparingLong(pair -> pair.second))
            .limit(m - sum)
            .map(pair -> pair.first)
            .collect(Collectors.toSet());
        final String answer = IntStream.range(0, k)
            .mapToLong(i -> {
                final long b = bList.get(i);
                if (set.contains(i)) {
                    return b + 1;
                } else {
                    return b;
                }
            }).mapToObj(Long::toString)
            .collect(Collectors.joining(" "));
        System.out.println(answer);
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
