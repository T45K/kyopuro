package AtCoder.ABC.ABC212.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO: 遅延セグ木だとなぜだめなのか
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int q = scanner.nextInt();
        final LazySegmentTree<Pair> tree = new LazySegmentTree<>(
            IntStream.range(0, q)
                .mapToObj(i -> new Pair(i, Long.MAX_VALUE / 2))
                .collect(Collectors.toList()),
            new Pair(-1, Long.MAX_VALUE / 2),
            (a, b) -> a.value < b.value ? a : b);
        final List<Long> answers = new ArrayList<>();
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        int accum = 0;
        for (int i = 0; i < q; i++) {
            final int query = scanner.nextInt();
            switch (query) {
                case 1: {
                    final int x = scanner.nextInt();
                    tree.update(i, new Pair(i, x));
                    break;
                }
                case 2: {
                    final int x = scanner.nextInt();
                    tree.updateRange(0, i, new Pair(-1, x));
                    break;
                }
                case 3: {
                    final Pair answer = tree.query(0, i);
                    answers.add(answer.value);
                    tree.update(answer.index, new Pair(answer.index, Long.MAX_VALUE / 2));
                }
            }
        }

        final String answer = answers.stream()
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Pair {
        int index;
        long value;

        Pair(final int index, final long value) {
            this.index = index;
            this.value = value;
        }
    }

    private static class LazySegmentTree<T> {
        private final T[] internalArray;
        private final T[] lazyArray;
        private final int exponent;
        private final T initialValue;
        private final BinaryOperator<T> comparator;

        LazySegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(list.size() - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(list, initialValue);
            lazyArray = initLazyArray(initialValue);
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
         * 区間更新
         * 区間を [left, right) の半開区間で渡すことに注意
         *
         * @param left  "0-indexed"のクエリの左端
         * @param right "0-indexed"のクエリの右端 + 1
         *              つまり"1-indexed"のクエリの右端
         * @param value 更新する値
         */
        void updateRange(final int left, final int right, final T value) {
            updateRange(left, right, value, 0, exponent, 1);
        }

        private void updateRange(final int left, final int right, final T value, final int begin, final int end, final int k) {
            evaluate(k);
            if (left <= begin && end <= right) {
                lazyArray[k] = value;
                evaluate(k);
            } else if (left < end && begin < right) {
                final int mid = (begin + end) / 2;
                updateRange(left, right, value, begin, mid, k * 2);
                updateRange(left, right, value, mid, end, k * 2 + 1);
                internalArray[k] = comparator.apply(internalArray[k * 2], internalArray[k * 2 + 1]);
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

        private T query(final int left, final int right, final int begin, final int end, final int k) {
            evaluate(k);

            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalArray[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        private void evaluate(final int index) {
            if (lazyArray[index] == initialValue) {
                return;
            }
            final T value = lazyArray[index];
            if (index * 2 + 1 < lazyArray.length) {
                lazyArray[index * 2] = value;
                lazyArray[index * 2 + 1] = value;
            }
            if (((Pair) internalArray[index]).value < Long.MAX_VALUE / 2) {
                ((Pair) internalArray[index]).value += ((Pair) value).value;
            }
            lazyArray[index] = initialValue;
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

        @SuppressWarnings("unchecked")
        private T[] initLazyArray(final T initialValue) {
            final Object[] array = new Object[exponent * 2];
            Arrays.fill(array, initialValue);
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
