package ABC.ABC251.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
10進数で考えれば良い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int w = scanner.nextInt();
        final List<Integer> values = new RestrictedList<>(v -> v <= w);
        for (int i = 1; i <= 99; i++) {
            values.add(i);
            values.add(i * 100);
            values.add(i * 100 * 100);
        }
        values.add(1_000_000);
        System.out.println(values.size());
        System.out.println(values);
    }

    private static class RestrictedList<E> extends ArrayList<E> {
        private final Predicate<E> predicate;

        RestrictedList(final Predicate<E> predicate) {
            super();
            this.predicate = predicate;
        }

        @Override
        public boolean add(final E e) {
            if (predicate.test(e)) {
                return super.add(e);
            }
            return false;
        }

        @Override
        public String toString() {
            return this.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
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
}
