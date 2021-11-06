package AtCoder.ABC.ABC223.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
解説AC
セグ木の各ノードに累積和と最小値を持たせる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final String s = scanner.next();
        final List<Pair<Integer, Integer>> list = IntStream.range(0, n)
            .mapToObj(i -> s.charAt(i) == '(' ? new Pair<>(1, 1) : new Pair<>(-1, -1))
            .collect(Collectors.toList());
        final SegmentTree<Pair<Integer, Integer>> tree = new SegmentTree<>(
            list,
            new Pair<>(0, Integer.MAX_VALUE / 2),
            (a, b) -> new Pair<>(a.sum + b.sum, Math.min(a.min, a.sum + b.min)));
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int query = scanner.nextInt();
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            if (query == 1) {
                final Pair<Integer, Integer> left = tree.query(l - 1, l);
                final Pair<Integer, Integer> right = tree.query(r - 1, r);
                tree.update(l - 1, right);
                tree.update(r - 1, left);
            } else {
                final Pair<Integer, Integer> result = tree.query(l - 1, r);
                if (result.sum == 0 && result.min == 0) {
                    joiner.add("Yes");
                } else {
                    joiner.add("No");
                }
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
        final T1 sum;
        final T2 min;

        Pair(final T1 sum, final T2 min) {
            this.sum = sum;
            this.min = min;
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
            return Objects.equals(sum, pair.sum) && Objects.equals(min, pair.min);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum, min);
        }
    }
}
