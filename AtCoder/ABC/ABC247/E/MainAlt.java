package AtCoder.ABC.ABC247.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
別解
右側だけを見て、満たす値と満たさない値の位置から個数を出す
 */
public class MainAlt {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        final Function<Predicate<Integer>, TreeSet<Integer>> generator = predicate ->
            IntStream.range(0, n)
                .boxed()
                .filter(i -> predicate.test(list.get(i)))
                .collect(Collectors.toCollection(TreeSet::new));

        final TreeSet<Integer> sameAsMax = generator.apply(v -> v == x);
        final TreeSet<Integer> sameAsMin = generator.apply(v -> v == y);
        final TreeSet<Integer> biggerThanMax = generator.apply(v -> v > x);
        final TreeSet<Integer> smallerThanMin = generator.apply(v -> v < y);

        final long answer = IntStream.range(0, n)
            .mapToLong(i -> {
                final int value = list.get(i);
                if (value > x || value < y) {
                    return 0;
                }

                final Integer nearestMax = sameAsMax.ceiling(i);
                final Integer nearestMin = sameAsMin.ceiling(i);
                if (nearestMax == null || nearestMin == null) {
                    return 0;
                }
                final int beginInclusive = Math.max(nearestMax, nearestMin);

                final int nearestBiggerThanMax = Optional.ofNullable(biggerThanMax.higher(i)).orElse(n);
                final int nearestSmallerThanMin = Optional.ofNullable(smallerThanMin.higher(i)).orElse(n);
                final int endExclusive = Math.min(nearestBiggerThanMax, nearestSmallerThanMin);
                if (endExclusive < beginInclusive) {
                    return 0;
                } else {
                    return endExclusive - beginInclusive;
                }
            }).sum();
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
}
