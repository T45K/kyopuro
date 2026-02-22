package ABC.ABC267.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] a = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt() - 1;
            final int v = scanner.nextInt() - 1;
            graph.computeIfAbsent(u, value -> new LinkedHashSet<>()).add(v);
            graph.computeIfAbsent(v, value -> new LinkedHashSet<>()).add(u);
        }
        final List<Long> initialCost = IntStream.range(0, n)
            .mapToLong(i -> graph.getOrDefault(i, Collections.emptySet()).stream()
                .mapToLong(j -> a[j])
                .sum())
            .boxed()
            .collect(Collectors.toList());
        final IndexedSegmentTree<Long> segmentTree = new IndexedSegmentTree<>(initialCost, Long.MAX_VALUE, Math::min);
        long max = 0;
        for (int i = 0; i < n; i++) {
            final IndexedSegmentTree.IndexedValue<Long> min = segmentTree.query(n);
            max = Math.max(max, min.getValue());
            final int index = min.getIndex();
            final int sub = a[index];
            for (final int node : graph.getOrDefault(index, Collections.emptySet())) {
                segmentTree.update(node, v -> v - sub);
                graph.get(node).remove(index);
            }
            segmentTree.update(index, Long.MAX_VALUE);
        }

        System.out.println(max);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static class IndexedSegmentTree<T> {
        private final IndexedValue<T>[] internalArray;
        private final int exponent;
        private final IndexedValue<T> initialValue;
        private final BinaryOperator<IndexedValue<T>> comparator;

        IndexedSegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(list.size() - 1).length();
            this.comparator = (a, b) -> comparator.apply(a.getValue(), b.getValue()).equals(a.getValue()) ? a : b;
            this.initialValue = new IndexedValue<>(-1, initialValue);
            internalArray = initArray(list, initialValue);
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final T value) {
            internalArray[index + exponent] = new IndexedValue<>(index, value);
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
                current /= 2;
            }
        }

        /**
         * 値の更新
         *
         * @param index    "0-indexed"のインデックス
         * @param operator 更新式
         */
        void update(final int index, final UnaryOperator<T> operator) {
            internalArray[index + exponent] = new IndexedValue<>(index, operator.apply(internalArray[index + exponent].getValue()));
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
         * @param right "0-indexed"のクエリの右端 + 1
         *              つまり"1-indexed"のクエリの右端
         * @return クエリ結果
         */
        IndexedValue<T> query(final int right) {
            return query(0, right, 0, exponent, 1);
        }

        IndexedValue<T> query(final int left, final int right, final int begin, final int end, final int k) {
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
        private IndexedValue<T>[] initArray(final List<T> list, final T initialValue) {
            final IndexedValue<T>[] array = new IndexedValue[(exponent + 1) * 2];
            Arrays.fill(array, new IndexedValue<>(-1, initialValue));
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = new IndexedValue<>(i, list.get(i));
            }

            for (int i = exponent - 1; i > 0; i--) {
                array[i] = comparator.apply(array[i * 2], array[i * 2 + 1]);
            }

            return array;
        }

        private static class IndexedValue<T> {
            private final int index;
            private final T value;

            public IndexedValue(final int index, final T value) {
                this.index = index;
                this.value = value;
            }

            public int getIndex() {
                return index;
            }

            public T getValue() {
                return value;
            }
        }
    }
}
