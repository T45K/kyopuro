package AtCoder.ABC.ABC253.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
後ろからやる
クエリは
1. 範囲加算
2. 取り換え
3. 出力
なので、出力は、対象のインデックスがクエリ2が走ってからのクエリ1の和を考える

クエリを後ろから見ていって
1. クエリ1だった場合、範囲加算を行う
2. クエリ3だった場合、見ているインデックスと、現時点での加算値を記録しておく
3. クエリ2だった場合、取り換えるインデックスを見ているかつ出現済みのクエリ3を計算する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        @SuppressWarnings("unused") final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int q = scanner.nextInt();

        final List<List<Integer>> list = Stream.generate(() -> {
                final int query = scanner.nextInt();
                switch (query) {
                    case 1:
                        return List.of(query, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                    case 2:
                    case 3:
                        return List.of(query, scanner.nextInt(), scanner.nextInt());
                }
                throw new RuntimeException();
            })
            .limit(q)
            .collect(Collectors.toList());

        final SegmentTree<Long> tree = new SegmentTree<>(m + 2, 0L, Long::sum);
        final Map<Index, Long> answers = new HashMap<>();
        final Map<Index, Long> sumsWhenQueried = new HashMap<>();
        final ListMap<Integer, OutputQuery> colToOutputQueries = new ListMap<>();

        for (int index = list.size() - 1; index >= 0; index--) {
            final List<Integer> values = list.get(index);
            switch (values.get(0)) {
                case 1: {
                    final RangeAddQuery query = new RangeAddQuery(values);
                    tree.update(query.l, a -> a + query.x);
                    tree.update(query.r + 1, a -> a - query.x);
                    break;
                }
                case 2: {
                    final ReplaceQuery query = new ReplaceQuery(values);
                    for (final OutputQuery outputQuery : colToOutputQueries.getList(query.i)) {
                        final long currentSum = tree.query(outputQuery.j + 1);
                        answers.put(outputQuery.index, query.x + currentSum - sumsWhenQueried.get(outputQuery.index));
                    }
                    colToOutputQueries.remove(query.i);
                    break;
                }
                case 3: {
                    final OutputQuery query = new OutputQuery(index, values);
                    colToOutputQueries.putSingle(query.i, query);
                    sumsWhenQueried.put(query.index, tree.query(query.j + 1));
                }
            }
        }
        for (final List<OutputQuery> queries : colToOutputQueries.values()) {
            for (final OutputQuery query : queries) {
                answers.put(query.index, tree.query(query.j + 1) - sumsWhenQueried.get(query.index));
            }
        }

        final String answer = answers.keySet().stream()
            .sorted()
            .map(answers::get)
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class RangeAddQuery {
        final int l;
        final int r;
        final int x;

        RangeAddQuery(final List<Integer> list) {
            this.l = list.get(1);
            this.r = list.get(2);
            this.x = list.get(3);
        }
    }

    private static class ReplaceQuery {
        final int i;
        final int x;

        ReplaceQuery(final List<Integer> list) {
            this.i = list.get(1);
            this.x = list.get(2);
        }
    }

    private static class OutputQuery {
        final Index index;
        final int i;
        final int j;

        public OutputQuery(final int index, final List<Integer> list) {
            this.index = new Index(index);
            this.i = list.get(1);
            this.j = list.get(2);
        }
    }

    private static class ListMap<K, V> extends HashMap<K, List<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public List<V> getList(final K key) {
            return Optional.ofNullable(super.get(key)).orElse(Collections.emptyList());
        }
    }

    private static class Index implements Comparable<Index> {
        final int value;

        Index(final int value) {
            this.value = value;
        }

        @Override
        public int compareTo(final Index o) {
            return Integer.compare(this.value, o.value);
        }
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
         * @param right "0-indexed"のクエリの右端 + 1
         *              つまり"1-indexed"のクエリの右端
         * @return クエリ結果
         */
        T query(final int right) {
            return query(right, 0, exponent, 1);
        }

        T query(final int right, final int begin, final int end, final int k) {
            if (right <= begin) {
                return initialValue;
            }

            if (end <= right) {
                return internalArray[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(right, begin, mid, k * 2), query(right, mid, end, k * 2 + 1));
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
