package ABC.ABC226.D;

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
import java.util.stream.Stream;

import static java.lang.Math.abs;

/*
それぞれの点同士の切片を計算する
gcdを取る
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair<Integer, Integer>> points = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());
        final Set<Pair<Long, Long>> answers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            final Pair<Integer, Integer> a = points.get(i);
            for (int j = i + 1; j < n; j++) {
                final Pair<Integer, Integer> b = points.get(j);
                if (a.first.equals(b.first)) {
                    answers.add(new Pair<>(0L, 1L));
                    answers.add(new Pair<>(0L, -1L));
                    continue;
                }
                if (a.second.equals(b.second)) {
                    answers.add(new Pair<>(1L, 0L));
                    answers.add(new Pair<>(-1L, 0L));
                    continue;
                }

                final long xDiff = b.first - a.first;
                final long yDiff = b.second - a.second;
                final long gcd = euclideanAlgorithm(abs(xDiff), abs(yDiff));
                answers.add(new Pair<>(xDiff / gcd, yDiff / gcd));
                answers.add(new Pair<>(-xDiff / gcd, -yDiff / gcd));
            }
        }
        System.out.println(answers.size());
    }

    private static long euclideanAlgorithm(final long a, final long b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
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
