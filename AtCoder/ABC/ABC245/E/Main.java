package AtCoder.ABC.ABC245.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
解説AC
最初に箱を列挙するのではなく、イテレーティブにやる
 */
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

        final Map<Integer, List<Integer>> chocolates = IntStream.range(0, n)
            .mapToObj(i -> new Pair<>(a.get(i), b.get(i)))
            .collect(Collectors.groupingBy(
                Pair::getFirst,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .map(Pair::getSecond)
                        .collect(Collectors.toList())
                )
            ));
        final Map<Integer, List<Integer>> boxes = IntStream.range(0, m)
            .mapToObj(i -> new Pair<>(c.get(i), d.get(i)))
            .collect(Collectors.groupingBy(
                Pair::getFirst,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .map(Pair::getSecond)
                        .collect(Collectors.toList())
                )
            ));

        final List<Integer> indexes = Stream.concat(chocolates.keySet().stream(), boxes.keySet().stream())
            .sorted(Comparator.reverseOrder())
            .distinct()
            .collect(Collectors.toList());

        final TreeMap<Integer, Integer> storedBoxes = new TreeMap<>();
        for (final int index : indexes) {
            for (final int size : boxes.getOrDefault(index, Collections.emptyList())) {
                storedBoxes.compute(size, (k, v) -> v == null ? 1 : (v + 1));
            }

            for (final int size : chocolates.getOrDefault(index, Collections.emptyList())) {
                final Integer ceilingSize = storedBoxes.ceilingKey(size);
                final Optional<Integer> count = Optional.ofNullable(ceilingSize)
                    .map(it -> storedBoxes.get(ceilingSize))
                    .filter(it -> it > 0);
                if (count.isEmpty()) {
                    System.out.println("No");
                    return;
                }

                if (count.get() == 1) {
                    storedBoxes.remove(ceilingSize);
                } else {
                    storedBoxes.put(ceilingSize, count.get() - 1);
                }
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
