package ABC.ABC239.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final int[] X = {1, 2, 2, 1, -1, -2, -2, -1};
    private static final int[] Y = {2, 1, -1, -2, -2, -1, 1, 2};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int x1 = scanner.nextInt();
        final int y1 = scanner.nextInt();
        final int x2 = scanner.nextInt();
        final int y2 = scanner.nextInt();

        final Set<Pair<Integer, Integer>> set = IntStream.range(0, X.length)
            .mapToObj(i -> new Pair<>(x1 + X[i], y1 + Y[i]))
            .collect(Collectors.toSet());
        final boolean isOverlapped = IntStream.range(0, X.length)
            .mapToObj(i -> new Pair<>(x2 + X[i], y2 + Y[i]))
            .anyMatch(set::contains);
        if (isOverlapped) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
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
