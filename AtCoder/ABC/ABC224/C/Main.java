package AtCoder.ABC.ABC224.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair<Long, Long>> list = Stream.generate(() -> new Pair<>(scanner.nextLong(), scanner.nextLong()))
            .limit(n)
            .collect(Collectors.toList());

        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            final Pair<Long, Long> a = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                final Pair<Long, Long> b = list.get(j);
                for (int k = j + 1; k < list.size(); k++) {
                    final Pair<Long, Long> c = list.get(k);
                    if (a.first == b.first.longValue() && a.first == c.first.longValue()
                        || a.second == b.second.longValue() && a.second == c.second.longValue()
                        || (a.first - c.first) * (a.second - b.second) == (a.first - b.first) * (a.second - c.second)) {
                        continue;
                    }
                    count++;
                }
            }
        }
        System.out.println(count);
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
