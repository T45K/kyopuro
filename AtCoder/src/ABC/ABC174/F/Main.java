package ABC.ABC174.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
クエリをソートする
クエリの右端を昇順に並べる
クエリの右端までの玉の各種類について，最右なものの位置を記録していく
最後に，クエリの左端より右側に位置している玉の数を数える（区間和なのでセグ木とかBIT）
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Integer> bolls = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final List<Query> queries = IntStream.range(0, q)
            .mapToObj(i -> new Query(i, scanner.nextInt(), scanner.nextInt()))
            .sorted(Comparator.comparingInt(query -> query.r))
            .collect(Collectors.toList());

        final SegmentTree<Integer> tree = new SegmentTree<>(
            IntStream.range(0, n).mapToObj(i -> 0).collect(Collectors.toList()), 0, Integer::sum);
        final int[] lastIndices = new int[n + 1];
        Arrays.fill(lastIndices, -1);
        int index;
        for (index = 0; index < queries.get(0).r; index++) {
            final int boll = bolls.get(index);
            if (lastIndices[boll] != -1) {
                tree.update(lastIndices[boll], 0);
            }
            lastIndices[boll] = index;
            tree.update(index, 1);
        }

        final int[] answers = new int[q];
        for (final Query query : queries) {
            for (; index < query.r; index++) {
                final int boll = bolls.get(index);
                if (lastIndices[boll] != -1) {
                    tree.update(lastIndices[boll], 0);
                }
                lastIndices[boll] = index;
                tree.update(index, 1);
            }
            final int answer = tree.query(query.l - 1, query.r);
            answers[query.number] = answer;
        }

        // 毎回soutしてるとTLE間に合わない
        final String answer = Arrays.stream(answers)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
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

    private static class Query {
        final int number;
        final int l;
        final int r;

        Query(final int number, final int l, final int r) {
            this.number = number;
            this.l = l;
            this.r = r;
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
    