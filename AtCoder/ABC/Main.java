package AtCoder.ABC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数字は0~n-1が一回ずつ出てくることから、先頭と末尾の数字の転倒数は簡単に求められる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final List<Integer> initial = IntStream.range(0, n)
            .mapToObj(i -> 0)
            .collect(Collectors.toList());
        final SegmentTree tree = new SegmentTree(initial, 0, Integer::sum);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            final int value = list.get(i);
            sum += i - tree.query(value);
            tree.update(value);
        }
        final List<Long> results = new ArrayList<>();
        results.add(sum);
        for (final int value : list) {
            sum += n - 1 - 2 * value;
            results.add(sum);
        }
        final String answer = results.stream()
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class SegmentTree {
        private final int[] internalTree;
        private final int exponent;
        private final int initialValue;
        private final BinaryOperator<Integer> comparator;

        SegmentTree(final List<Integer> list, final int initialValue, final BinaryOperator<Integer> comparator) {
            this.exponent = calcExponent(list.size());
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalTree = initTree(list, initialValue);
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         */
        void update(final int index) {
            internalTree[index + exponent] = 1;
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
         * @param right "0-indexed"のクエリの右端 + 1
         *              つまり"1-indexed"のクエリの右端
         * @return クエリ結果
         */
        int query(final int right) {
            return query(0, right, 0, exponent, 1);
        }

        int query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalTree[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        private int[] initTree(final List<Integer> list, final int initialValue) {
            final int[] array = new int[exponent * 2];
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
    