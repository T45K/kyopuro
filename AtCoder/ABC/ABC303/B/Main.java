package AtCoder.ABC.ABC303.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
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
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Set<Pair<Integer, Integer>> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            final List<Integer> list = Stream.generate(scanner::nextInt)
                .limit(n)
                .collect(Collectors.toList());
            final Set<Pair<Integer, Integer>> tmp = IntStream.range(1, n)
                .mapToObj(j -> {
                    final int min = Math.min(list.get(j - 1), list.get(j));
                    final int max = Math.max(list.get(j - 1), list.get(j));
                    return new Pair<>(min, max);
                })
                .collect(Collectors.toSet());
            set.addAll(tmp);
        }
        final int allPairs = n * (n - 1) / 2;
        System.out.println(allPairs - set.size());
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
