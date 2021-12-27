package AtCoder.ABC.ABC231.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> a = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> b = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final Collection<List<Integer>> bGroupedByA = IntStream.range(0, n)
            .mapToObj(i -> new Pair<>(a.get(i), b.get(i)))
            .sorted(Comparator.comparingInt(Pair::getFirst))
            .collect(Collectors.groupingBy(pair -> pair.first,
                LinkedHashMap::new,
                Collectors.mapping(pair -> pair.second, Collectors.toList())))
            .values();
        final List<Integer> sortedDistinctB = b.stream()
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        final Map<Integer, Integer> invertedIndex = IntStream.range(0, sortedDistinctB.size())
            .boxed()
            .collect(Collectors.toMap(sortedDistinctB::get, Function.identity()));
        final SegmentTree<Long> segmentTree = new SegmentTree<>(sortedDistinctB.size(), 0L, Long::sum);

        long sum = 0;
        for (final List<Integer> values : bGroupedByA) {
            for (final int value : values) {
                final int index = invertedIndex.get(value);
                segmentTree.update(index, i -> i + 1);
            }
            for (final int value : values) {
                final int index = invertedIndex.get(value);
                sum += segmentTree.query(index, sortedDistinctB.size());
            }
        }
        System.out.println(sum);
    }

    private static class SegmentTree<T> {
        private final T[] internalArray;
        private final int exponent;
        private final T initialValue;
        private final BinaryOperator<T> comparator;

        SegmentTree(final int size, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(size - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(Collections.emptyList(), initialValue);
        }

        /**
         * 値の更新
         *
         * @param index    "0-indexed"のインデックス
         * @param operator 更新式
         */
        void update(final int index, final UnaryOperator<T> operator) {
            internalArray[index + exponent] = operator.apply(internalArray[index + exponent]);
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
                current /= 2;
            }
        }

        /**
         * クエリ
         * クエリの区間を [left, right) の半開区間で渡すことに注意
         *
         * @param left  "0-indexed"のクエリの左端
         * @param right "0-indexed"のクエリの右端 + 1
         *              つまり"1-indexed"のクエリの右端
         * @return クエリ結果
         */
        T query(final int left, final int right) {
            return query(left, right, 0, exponent, 1);
        }

        T query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalArray[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        @SuppressWarnings("unchecked")
        private T[] initArray(final List<T> list, final T initialValue) {
            final Object[] array = new Object[(exponent + 1) * 2];
            Arrays.fill(array, initialValue);
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = list.get(i);
            }

            for (int i = exponent - 1; i > 0; i--) {
                array[i] = comparator.apply((T) array[i * 2], (T) array[i * 2 + 1]);
            }

            return (T[]) array;
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
