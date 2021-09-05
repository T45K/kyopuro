package AtCoder.ABC.ABC217.E;

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

/*
ソート済みの箇所はセグ木で，そうでない箇所は最初に用意しといたリストを見る
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int q = scanner.nextInt();
        final List<Pair> list = Stream.generate(() -> {
                final int c = scanner.nextInt();
                if (c == 1) {
                    return new Pair(c, scanner.nextInt());
                } else {
                    return new Pair(c, 0);
                }
            })
            .limit(q)
            .collect(Collectors.toList());
        final List<Integer> values = list.stream()
            .filter(pair -> pair.c == 1)
            .map(pair -> pair.x)
            .collect(Collectors.toList());
        final List<Pair> indexedValues = IntStream.range(0, values.size())
            .mapToObj(i -> new Pair(i, values.get(i)))
            .collect(Collectors.toList());
        final SegmentTree<Pair> tree = new SegmentTree<>(indexedValues, new Pair(0, Integer.MAX_VALUE), (a, b) -> a.x <= b.x ? a : b);
        int addedCount = 0;
        int lookingAt = 0;
        int sortedPoint = -1;
        final StringJoiner joiner = new StringJoiner("\n");
        for (final Pair pair : list) {
            if (pair.c == 1) {
                addedCount++;
                continue;
            }
            if (pair.c == 3) {
                sortedPoint = addedCount; // （要素数-1）番目までソートされてる
                continue;
            }

            if (lookingAt < sortedPoint) {
                final Pair min = tree.query(0, sortedPoint);
                joiner.add(Integer.toString(min.x));
                tree.update(min.c, new Pair(min.c, Integer.MAX_VALUE));
            } else {
                joiner.add(values.get(lookingAt).toString());
                tree.update(lookingAt, new Pair(lookingAt, Integer.MAX_VALUE));
            }
            lookingAt++;
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
