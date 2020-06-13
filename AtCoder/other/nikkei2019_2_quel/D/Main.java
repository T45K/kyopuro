package AtCoder.other.nikkei2019_2_quel.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
座標
ダイクストラではない(条件から間に合わない)
特定範囲に一律に距離を加えるので，移動するノード番号は単調増加が最適
操作を範囲の左側の降順でソートして左側から右側いっぱいまで移動した時の距離を考える
今いる左側の位置の1からの最短距離は，今の位置より右側のノードの最短距離に等しいので，
その値に今見ている操作の距離で移動する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Operation> operations = IntStream.range(0, m)
            .mapToObj(i -> new Operation(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .sorted(Comparator.comparingInt(o -> o.left))
            .collect(Collectors.toList());

        if (operations.get(0).left > 1) {
            System.out.println(-1);
            return;
        }

        final List<Long> initial = IntStream.rangeClosed(0, n)
            .mapToObj(i -> i == 1 ? 0 : Long.MAX_VALUE)
            .collect(Collectors.toList());

        final SegmentTree<Long> tree = new SegmentTree<>(initial, Long.MAX_VALUE, Math::min);
        for (final Operation operation : operations) {
            final long queryResult = tree.query(operation.left, n + 1);
            if (queryResult == Long.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            tree.update(operation.right, value -> Math.min(value, queryResult + operation.c));
        }

        final long answer = tree.get(n) < Long.MAX_VALUE ? tree.get(n) : -1;
        System.out.println(answer);
    }

    private static class Operation {
        final int left;
        final int right;
        final int c;

        Operation(final int left, final int right, final int c) {
            this.left = left;
            this.right = right;
            this.c = c;
        }
    }

    private static class SegmentTree<T> {
        private final T[] internalTree;
        private final int exponent;
        private final T initialValue;
        private final BinaryOperator<T> comparator;

        SegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = calcExponent(list.size());
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalTree = initTree(list, initialValue);
        }

        T get(final int index) {
            return internalTree[index + exponent];
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final T value) {
            internalTree[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalTree[current] = comparator.apply(internalTree[current * 2], internalTree[current * 2 + 1]);
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
            internalTree[index + exponent] = operator.apply(internalTree[index + exponent]);
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalTree[current] = comparator.apply(internalTree[current * 2], internalTree[current * 2 + 1]);
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
                return internalTree[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        @SuppressWarnings("unchecked")
        private T[] initTree(final List<T> list, final T initialValue) {
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

        private int calcExponent(final int n) {
            int exp = 1;
            while (exp < n) {
                exp <<= 1;
            }
            return exp;
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
