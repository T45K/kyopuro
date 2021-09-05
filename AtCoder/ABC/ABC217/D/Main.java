package AtCoder.ABC.ABC217.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    /*
    ある値が出現したかどうかを座圧したセグ木で持つ
    c==2の時，（クエリの数字以上の数字で，すでに出現しているものの最小値）-（クエリの数字以下の数字で，すでに出現しているものの最大値）を求めれば良い
     */
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int l = scanner.nextInt();
        final int q = scanner.nextInt();
        final StringJoiner joiner = new StringJoiner("\n");
        final List<Pair> queries = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextInt()))
            .limit(q)
            .collect(Collectors.toList());
        final List<Integer> separates = queries.stream()
            .filter(pair -> pair.c == 1)
            .map(pair -> pair.x)
            .sorted()
            .collect(Collectors.toList());

        final List<Integer> maxList = IntStream.range(0, separates.size() + 2).map(i -> l).boxed().collect(Collectors.toList()); // 0とL
        maxList.set(0, 0);
        final List<Integer> zeroList = IntStream.range(0, separates.size() + 2).map(i -> 0).boxed().collect(Collectors.toList());
        zeroList.set(separates.size() + 1, l);
        final SegmentTree<Integer> maxTree = new SegmentTree<>(zeroList, 0, Math::max);
        final SegmentTree<Integer> minTree = new SegmentTree<>(maxList, l, Math::min);
        for (final Pair query : queries) {
            final int x = query.x;
            if (query.c == 1) {
                final int index = Collections.binarySearch(separates, x);
                minTree.update(index + 1, x);
                maxTree.update(index + 1, x);
            } else {
                int index = Collections.binarySearch(separates, x);
                if (index < 0) {
                    index = ~index;
                }
                final Integer min = maxTree.query(0, index + 1);
                final Integer max = minTree.query(index + 1, separates.size() + 2);
                joiner.add(Integer.toString(max - min));
            }
        }

        System.out.println(joiner);
    }

    private static class SegmentTree<T> {
        private final T[] internalArray;
        private final int exponent;
        private final T initialValue;
        private final BinaryOperator<T> comparator;

        SegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(list.size() - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(list, initialValue);
        }

        SegmentTree(final int size, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(size - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(Collections.emptyList(), initialValue);
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final T value) {
            internalArray[index + exponent] = value;
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
            final Object[] array = new Object[exponent * 2];
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

    private static class Pair {
        final int c;
        final int x;

        Pair(final int c, final int x) {
            this.c = c;
            this.x = x;
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
