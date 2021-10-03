package AtCoder.ABC.ABC221.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
解説AC
逆数をとると扱いやすくなる
 */
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final Map<Integer, Integer> invertedIndex = invert(list.stream()
            .sorted()
            .distinct()
            .collect(Collectors.toList()));
        final SegmentTree<Long> tree = new SegmentTree<>(invertedIndex.size(), 0L, Long::sum);

        long sum = 0;
        long base = 1;
        for (final int value : list) {
            final int index = invertedIndex.get(value);
            final long queryResult = tree.query(index + 1);
            sum += base * queryResult % MOD;
            sum %= MOD;
            base *= 2;
            base %= MOD;
            final long inv = modInv(base);
            tree.update(index, v -> v + inv);
        }

        System.out.println(sum);
    }

    private static long modInv(long a) {
        long res = 1;
        long n = MOD - 2;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return res;
    }

    private static Map<Integer, Integer> invert(final List<Integer> list) {
        return IntStream.range(0, list.size())
            .boxed()
            .collect(Collectors.toMap(list::get, Function.identity()));
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
