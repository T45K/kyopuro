package AtCoder.ABC.ABC203.D;

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
import java.util.stream.IntStream;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final List<Integer> list = IntStream.range(0, n)
            .flatMap(i -> Arrays.stream(table[i], 0, n))
            .distinct()
            .sorted()
            .boxed()
            .collect(Collectors.toList());
        int min = Integer.MAX_VALUE;
        for (int x = 0; x < n - k + 1; x++) {
            final SegmentTree<Integer> tree = new SegmentTree<>(list.size(), 0, Integer::sum);
            for (int i = x; i < x + k; i++) {
                for (int j = 0; j < k; j++) {
                    final int index = Collections.binarySearch(list, table[i][j]);
                    tree.update(index, a -> a + 1);
                }
            }
            min = Math.min(list.get(binarySearch(0, list.size(), k, tree)), min);
            for (int y = 0; y < n - k; y++) {
                for (int i = x; i < x + k; i++) {
                    final int addedIndex = Collections.binarySearch(list, table[i][y + k]);
                    tree.update(addedIndex, a -> a + 1);
                    final int removedIndex = Collections.binarySearch(list, table[i][y]);
                    tree.update(removedIndex, a -> a - 1);
                }
                min = Math.min(list.get(binarySearch(0, list.size(), k, tree)), min);
            }
        }
        System.out.println(min);
    }

    private static int binarySearch(final int begin, final int end, final int k, final SegmentTree<Integer> tree) {
        if (end - begin <= 1) {
            return end;
        }

        final int mid = (begin + end) / 2;
        final int sum = tree.query(mid + 1);
        if (sum < (k * k + 1) / 2) {
            return binarySearch(mid, end, k, tree);
        } else {
            return binarySearch(0, mid, k, tree);
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
            return query(0, right, 0, exponent, 1);
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
}
    