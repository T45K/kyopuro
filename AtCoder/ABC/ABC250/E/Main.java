package AtCoder.ABC.ABC250.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
集合が同じ -> 種類数が同じ
事前に種類数を累積和で数え上げておく
種類数が同じ区間で比較する
 */
public class Main {

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> upperBoundComparator = (x, y) -> x > y ? 1 : -1;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] a = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final int[] b = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final List<Integer> aKinds = convertToKinds(n, a);
        final List<Integer> bKinds = convertToKinds(n, b);

        final int q = scanner.nextInt();
        final Map<Pair<Integer, Integer>, List<Integer>> indices = IntStream.range(0, q)
            .boxed()
            .map(i -> new Pair<>(new Pair<>(scanner.nextInt() - 1, scanner.nextInt() - 1), i))
            .collect(Collectors.groupingBy(
                pair -> pair.first,
                Collectors.mapping(pair -> pair.second, Collectors.toList())
            ));
        final Map<Integer, List<Pair<Integer, Integer>>> groups = indices.keySet().stream()
            .collect(Collectors.groupingBy(pair -> pair.first));

        final boolean[] answers = new boolean[q];
        final Set<Integer> aSet = new HashSet<>();
        final Set<Integer> bSet = new HashSet<>();
        for (int i = 1; i <= Math.min(aKinds.get(n - 1), bKinds.get(n - 1)); i++) {
            final int aLower = ~Collections.binarySearch(aKinds, i, lowerBoundComparator);
            final int aUpper = ~Collections.binarySearch(aKinds, i, upperBoundComparator);
            final int bLower = ~Collections.binarySearch(bKinds, i, lowerBoundComparator);
            final int bUpper = ~Collections.binarySearch(bKinds, i, upperBoundComparator);
            aSet.add(a[aLower]);
            bSet.add(b[bLower]);
            if (!aSet.equals(bSet)) {
                continue;
            }
            for (int j = aLower; j < aUpper; j++) {
                for (final Pair<Integer, Integer> pair : groups.getOrDefault(j, Collections.emptyList())) {
                    if (pair.second >= bLower && pair.second < bUpper) {
                        for (final int index : indices.get(pair)) {
                            answers[index] = true;
                        }
                    }
                }
            }
        }

        final String answer = IntStream.range(0, q)
            .mapToObj(i -> answers[i] ? "Yes" : "No")
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static List<Integer> convertToKinds(final int n, final int[] array) {
        final int[] kinds = new int[n];
        final Set<Integer> exists = new HashSet<>();
        kinds[0] = 1;
        exists.add(array[0]);
        for (int i = 1; i < n; i++) {
            if (exists.contains(array[i])) {
                kinds[i] = kinds[i - 1];
            } else {
                exists.add(array[i]);
                kinds[i] = kinds[i - 1] + 1;
            }
        }
        return Arrays.stream(kinds)
            .boxed()
            .collect(Collectors.toList());
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
