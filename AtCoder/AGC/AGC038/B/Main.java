package AtCoder.AGC.AGC038.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列 まず，操作後が元と変わらないのは考えなくて良い
ある列Aをソートした結果と，右に1トラバースした列Bをソートした結果が一緒になるのは，
Aの左端がAとBの和集合の最小値かつBの右端が和集合の最大値になるとき
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final SegmentTree<Integer> minTree = new SegmentTree<>(list, Integer.MAX_VALUE, Math::min);
        final SegmentTree<Integer> maxTree = new SegmentTree<>(list, Integer.MIN_VALUE, Math::max);

        final Set<Integer> sortedIndices = new HashSet<>();
        int accum = 1;
        int prev = list.get(0);
        for (int i = 1; i < n; i++) {
            final int value = list.get(i);
            if (value > prev) {
                accum++;
            } else {
                accum = 1;
            }
            if (accum >= k) {
                sortedIndices.add(i - k + 1);
            }
            prev = value;
        }

        int sum = sortedIndices.size() > 0 ? 1 : 0;
        boolean flag = false;
        for (int i = 0; i + k <= n; i++) {
            if (!sortedIndices.contains(i) && !(flag && maxTree.query(i, i + k).equals(list.get(i + k - 1)))) {
                sum++;
            }
            flag = list.get(i).equals(minTree.query(i, i + k));
        }
        System.out.println(sum);
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
    