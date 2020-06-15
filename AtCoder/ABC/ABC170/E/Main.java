package AtCoder.ABC.ABC170.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
データ構造で殴る
 */
public class Main {
    private static final int CONST = 200_000;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final int[] ratesOfChildren = new int[n + 1];
        final int[] belongsOfChildren = new int[n + 1];
        final Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        final Function<Integer, TreeSet<Integer>> generator = v -> new TreeSet<>(Comparator.comparingInt(i -> -ratesOfChildren[i]));

        for (int child = 1; child <= n; child++) {
            final int rate = scanner.nextInt();
            ratesOfChildren[child] = rate;
            final int belongs = scanner.nextInt();
            belongsOfChildren[child] = belongs;
            map.computeIfAbsent(belongs, generator).add(child);
        }

        final List<Integer> initial = IntStream.rangeClosed(0, CONST + 1)
            .mapToObj(i -> map.get(i) == null ? Integer.MAX_VALUE : ratesOfChildren[map.get(i).first()])
            .collect(Collectors.toList());
        final SegmentTree<Integer> tree = new SegmentTree<>(initial, Integer.MAX_VALUE, Math::min);

        for (int i = 0; i < q; i++) {
            final int targetChild = scanner.nextInt();
            final int next = scanner.nextInt();
            final int current = belongsOfChildren[targetChild];
            belongsOfChildren[targetChild] = next;

            map.get(current).remove(targetChild);
            final int post;
            if (map.get(current).isEmpty()) {
                post = Integer.MAX_VALUE;
            } else {
                post = ratesOfChildren[map.get(current).first()];
            }
            tree.update(current, post);

            map.computeIfAbsent(next, generator).add(targetChild);
            tree.update(next, ratesOfChildren[map.get(next).first()]);
            System.out.println(tree.query());
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
         * @return クエリ結果
         */
        T query() {
            return query(1, 200001, 0, exponent, 1);
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
    