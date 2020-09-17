package AtCoder.other.practice2.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final SegmentTree<Integer> segmentTree = new SegmentTree<>(list, 0, Math::max);
        for (int i = 0; i < q; i++) {
            final int t = scanner.nextInt();
            switch (t) {
                case 1: {
                    final int x = scanner.nextInt();
                    final int v = scanner.nextInt();
                    segmentTree.update(x - 1, v);
                    break;
                }
                case 2: {
                    final int l = scanner.nextInt();
                    final int r = scanner.nextInt();
                    final int max = segmentTree.query(l - 1, r);
                    System.out.println(max);
                    break;
                }
                case 3: {
                    final int x = scanner.nextInt();
                    final int v = scanner.nextInt();
                    final int max = segmentTree.query(x - 1, n);
                    if (max < v) {
                        System.out.println(n + 1);
                        break;
                    }

                    final int first = segmentTree.get(x - 1);
                    if (first >= v) {
                        System.out.println(x);
                        break;
                    }

                    final int j = binarySearch(segmentTree, x - 1, n, v);
                    System.out.println(j);
                }
            }
        }
    }

    private static int binarySearch(final SegmentTree<Integer> tree, final int left, final int right, final int v) {
        if (right - left <= 1) {
            return right;
        }

        final int mid = (left + right) / 2;
        final int leftMax = tree.query(left, mid);
        if (leftMax >= v) {
            return binarySearch(tree, left, mid, v);
        } else {
            return binarySearch(tree, mid, right, v);
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
