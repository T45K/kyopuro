package AtCoder.ABC.ABC245.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// TODO: solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> a = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> b = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> c = Stream.generate(scanner::nextInt)
            .limit(m)
            .collect(Collectors.toList());
        final List<Integer> d = Stream.generate(scanner::nextInt)
            .limit(m)
            .collect(Collectors.toList());

        final List<Pair<Integer, Integer>> chocolates = IntStream.range(0, n)
            .mapToObj(i -> new Pair<>(a.get(i), b.get(i)))
            .sorted(Comparator.comparingInt((Pair<Integer, Integer> pair) -> pair.first).thenComparing(Pair::getSecond, Comparator.reverseOrder()))
            .collect(Collectors.toList());
        final TreeMap<Integer, TreeMap<Integer, Integer>> boxes = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            boxes.computeIfAbsent(c.get(i), k -> new TreeMap<>()).compute(d.get(i), (k, v) -> v == null ? 1 : v + 1);
        }

        for (final Pair<Integer, Integer> choco : chocolates) {
            final Integer firstCeiling = boxes.ceilingKey(choco.first);
            if (firstCeiling == null) {
                System.out.println("No");
                return;
            }
            final Integer secondCeiling = boxes.get(firstCeiling).ceilingKey(choco.second);
            if (secondCeiling == null) {
                System.out.println("No");
                return;
            }
            final Integer count = boxes.get(firstCeiling).get(secondCeiling);
            if (count == null || count == 0) {
                System.out.println("No");
                return;
            }

            if (count == 1) {
                boxes.get(firstCeiling).remove(secondCeiling);
                if (boxes.get(firstCeiling) == null || boxes.get(firstCeiling).isEmpty()) {
                    boxes.remove(firstCeiling);
                }
            } else {
                boxes.get(firstCeiling).put(secondCeiling, count - 1);
            }
        }
        System.out.println("Yes");
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

        public T1 getFirst() {
            return first;
        }

        public T2 getSecond() {
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
    }
}
