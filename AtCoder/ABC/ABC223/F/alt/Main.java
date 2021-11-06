package AtCoder.ABC.ABC223.F.alt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/*
区間加算，区間更新セグ木でもできる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final String s = scanner.next();
        final char[] charArray = s.toCharArray();
        final BiConsumer<Integer, Integer> swap = (i, j) -> {
            final char tmp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = tmp;
        };
        final int[] array = new int[n + 1];
        for (int i = 0; i < n; i++) {
            array[i + 1] = array[i] + (charArray[i] == '(' ? 1 : -1);
        }

        final LazySegmentTree tree = new LazySegmentTree(
            Arrays.stream(array).boxed().collect(Collectors.toList()),
            Integer.MAX_VALUE / 2,
            Math::min);
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int query = scanner.nextInt();
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            switch (query) {
                case 1: {
                    if (charArray[l - 1] == '(' && charArray[r - 1] == ')') {
                        tree.updateRange(l, r, -2);
                    } else if (charArray[l - 1] == ')' && charArray[r - 1] == '(') {
                        tree.updateRange(l, r, 2);
                    }
                    swap.accept(l - 1, r - 1);
                    break;
                }
                case 2: {
                    if (tree.get(l - 1) == tree.get(r) && tree.query(l, r + 1) == tree.get(l - 1)) {
                        joiner.add("Yes");
                    } else {
                        joiner.add("No");
                    }
                    break;
                }
            }
        }

        System.out.println(joiner);
    }

    private static class LazySegmentTree {
        private final int[] internalArray;
        private final int[] lazyArray;
        private final int exponent;
        private final int initialValue;
        private final BinaryOperator<Integer> comparator;

        LazySegmentTree(final List<Integer> list, final int initialValue, final BinaryOperator<Integer> comparator) {
            this.exponent = 1 << Integer.toBinaryString(list.size() - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(list, initialValue);
            lazyArray = initLazyArray();
        }

        int get(final int index) {
            return query(index, index + 1);
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
        void updateRange(final int left, final int right, final int value) {
            updateRange(left, right, value, 0, exponent, 1);
        }

        private void updateRange(final int left, final int right, final int value, final int begin, final int end, final int k) {
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
        int query(final int left, final int right) {
            return query(left, right, 0, exponent, 1);
        }

        private int query(final int left, final int right, final int begin, final int end, final int k) {
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
            if (lazyArray[index] == 0) {
                return;
            }
            final int value = lazyArray[index];
            if (index * 2 + 1 < lazyArray.length) {
                lazyArray[index * 2] += value;
                lazyArray[index * 2 + 1] += value;
            }
            internalArray[index] += value;
            lazyArray[index] = 0;
        }

        private int[] initArray(final List<Integer> list, final int initialValue) {
            final int[] array = new int[(exponent + 1) * 2];
            Arrays.fill(array, initialValue);
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = list.get(i);
            }

            for (int i = exponent - 1; i > 0; i--) {
                array[i] = comparator.apply(array[i * 2], array[i * 2 + 1]);
            }

            return array;
        }

        private int[] initLazyArray() {
            final int[] array = new int[(exponent + 1) * 2];
            Arrays.fill(array, 0);
            return array;
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
