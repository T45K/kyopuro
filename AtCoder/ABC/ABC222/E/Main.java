package AtCoder.ABC.ABC222.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/*
- 子だけで組み合わせるを作れる(=今見ている深さからNまでD以上)
- 親を経由する
の二つに分ける

親を経由する場合，
経由できる親の上限（高すぎると距離Dを越える）と下限（低すぎると，その頂点からNまでの距離が足りなくなる）を考慮する
 */
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int d = scanner.nextInt();

        final long[] array = new long[d + 1];
        for (int i = 1; i <= d; i++) {
            array[i] = iterativePow(2, d - i - 1);
        }
        final List<Long> list = Arrays.stream(array)
            .boxed()
            .collect(Collectors.toList());
        final SegmentTree<Long> tree = new SegmentTree<>(list, 0L, (a, b) -> (a + b) % MOD);
        long numOfNodesInDepth = 1;
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            if (n - i >= d) { // 下だけで計上できる
                sum += numOfNodesInDepth * iterativePow(2, d) % MOD;
            }

            final int upper = Math.min(i, d + 1);
            final int lower = Math.max(0, i - (n + i - d) / 2);
            sum += tree.query(lower, upper) * numOfNodesInDepth % MOD;
            sum %= MOD;
            numOfNodesInDepth *= 2;
            numOfNodesInDepth %= MOD;
        }
        System.out.println(sum);
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

    private static long iterativePow(final long a, final long b) {
        return iterativePow(a, b, 1);
    }

    private static long iterativePow(final long a, final long b, final long result) {
        if (b <= 0) {
            return result;
        } else if ((b & 1) == 1) {
            return iterativePow(a * a % MOD, b >> 1, result * a % MOD);
        } else {
            return iterativePow(a * a % MOD, b >> 1, result % MOD);
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
