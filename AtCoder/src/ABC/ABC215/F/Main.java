package ABC.ABC215.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static java.lang.Math.abs;

@SuppressWarnings("unchecked")
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Pair<Integer, Integer>[] array = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .sorted(Comparator.comparingInt((ToIntFunction<Pair<Integer, Integer>>) Pair::getFirst).thenComparing(Pair::getSecond, Comparator.reverseOrder()))
            .filter(distinctBy(Pair::getFirst))
            .toArray(Pair[]::new);

        int max = 0;
        for (int i = 0; i < array.length; i++) {
            final Pair<Integer, Integer> a = array[i];
            for (int j = array.length - 1; j > i; j--) {
                final Pair<Integer, Integer> b = array[j];
                final int diff = b.first - a.first;
                if (diff <= max) {
                    break;
                }
                max = Math.max(max, Math.min(diff, abs(a.second - b.second)));
            }
        }
        System.out.println(max);
    }

    private static <T> Predicate<T> distinctBy(final Function<? super T, ?> elementExtractor) {
        final Map<Object, Boolean> seen = new HashMap<>();
        return t -> seen.putIfAbsent(elementExtractor.apply(t), true) == null;
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

        T1 getFirst() {
            return first;
        }

        T2 getSecond() {
            return second;
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

        @Override
        public String toString() {
            return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
        }
    }
}
