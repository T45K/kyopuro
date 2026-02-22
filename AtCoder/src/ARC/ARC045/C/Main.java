package ARC.ARC045.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

/*
一次元配列 いもす法+セグ木
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Long[] array = new Long[n];
        Arrays.fill(array, 0L);
        final Query[] queries = new Query[m];
        for (int i = 0; i < m; i++) {
            final int s = scanner.nextInt();
            final int t = scanner.nextInt();
            array[s - 1]++;
            if (t < n) {
                array[t]--;
            }
            queries[i] = new Query(s - 1, t);
        }

        for (int i = 1; i < n; i++) {
            array[i] += array[i - 1];
        }

        final SegmentTree tree = new SegmentTree(Arrays.asList(array), Integer.MAX_VALUE, Math::min);
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            final long result = tree.query(queries[i].s, queries[i].t);
            if (result > 1) {
                list.add(i + 1);
            }
        }
        System.out.println(list.size());
        list.forEach(System.out::println);
    }

    static class Query {
        final int s;
        final int t;

        public Query(final int s, final int t) {
            this.s = s;
            this.t = t;
        }
    }

    /**
     * Segment Tree<br>
     * モノイドに対する値の更新，区間に対するクエリをそれぞれO(log n)で行える．<br>
     * 更新，クエリの引数は 0-indexed で渡すことに注意
     */
    private static class SegmentTree {
        private final long[] internalTree;
        private final int exponent;
        private final long initialValue;
        private final BiFunction<Long, Long, Long> comparator;

        SegmentTree(final List<Long> list, final long initialValue, final BiFunction<Long, Long, Long> comparator) {
            this.exponent = calcExponent(list.size());
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalTree = initTree(list, initialValue);
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final long value) {
            internalTree[index + exponent] = value;
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
        long query(final int left, final int right) {
            return query(left, right, 0, exponent, 1);
        }

        long query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalTree[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        private long[] initTree(final List<Long> list, final long initialValue) {
            final long[] array = new long[exponent * 2];
            Arrays.fill(array, initialValue);
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = list.get(i);
            }

            for (int i = exponent - 1; i > 0; i--) {
                array[i] = comparator.apply(array[i * 2], array[i * 2 + 1]);
            }

            return array;
        }

        private int calcExponent(final int n) {
            int exp = 1;
            while (exp < n) {
                exp <<= 1;
            }
            return exp;
        }
    }
}
