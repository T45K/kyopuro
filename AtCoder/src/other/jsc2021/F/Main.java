package other.jsc2021.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
出現した値がインデックス、出現回数がバリュー
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Query> queries = Stream
            .generate(() -> new Query(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .limit(q)
            .collect(Collectors.toList());

        final List<Integer> valuesA = extractValuesFromQueries(queries, 1);
        final Map<Integer, Integer> invertedA = inverse(valuesA);
        final List<Long> emptyA = createEmptyList(valuesA.size());
        final SegmentTree<Long> countA = new SegmentTree<>(emptyA, 0L, Long::sum);
        countA.update(0, (long) n);
        final SegmentTree<Long> sumA = new SegmentTree<>(emptyA, 0L, Long::sum);

        final List<Integer> valuesB = extractValuesFromQueries(queries, 2);
        final Map<Integer, Integer> invertedB = inverse(valuesB);
        final List<Long> emptyB = createEmptyList(valuesB.size());
        final SegmentTree<Long> countB = new SegmentTree<>(emptyB, 0L, Long::sum);
        countB.update(0, (long) m);
        final SegmentTree<Long> sumB = new SegmentTree<>(emptyB, 0L, Long::sum);

        final int[] arrayA = new int[n + 1];
        final int[] arrayB = new int[m + 1];
        long sum = 0;
        final List<Long> answers = new ArrayList<>();
        for (final Query query : queries) {
            final int t = query.t;
            final int x = query.x;
            final int y = query.y;
            if (t == 1) {
                sum += execQuery(x, y, arrayA, invertedA, valuesB, countA, sumA, countB, sumB);
            } else {
                sum += execQuery(x, y, arrayB, invertedB, valuesA, countB, sumB, countA, sumA);
            }
            answers.add(sum);
        }
        final String answer = answers.stream()
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static long execQuery(final int x, final int y, final int[] array, final Map<Integer, Integer> inverted, final List<Integer> values,
                                  final SegmentTree<Long> count1,
                                  final SegmentTree<Long> sum1,
                                  final SegmentTree<Long> count2,
                                  final SegmentTree<Long> sum2) {
        final int tmpBefore = Collections.binarySearch(values, array[x]);
        final int indexBefore = tmpBefore >= 0 ? tmpBefore : ~tmpBefore;
        final long sumBefore = count2.queryFormer(indexBefore) * array[x] + sum2.queryLatter(indexBefore);
        final int tmpAfter = Collections.binarySearch(values, y);
        final int indexAfter = tmpAfter >= 0 ? tmpAfter : ~tmpAfter;
        final long sumAfter = count2.queryFormer(indexAfter) * y + sum2.queryLatter(indexAfter);
        count1.update(inverted.get(array[x]), i -> i - 1);
        count1.update(inverted.get(y), i -> i + 1);
        sum1.update(inverted.get(array[x]), i -> i - array[x]);
        sum1.update(inverted.get(y), i -> i + y);
        array[x] = y;
        return sumAfter - sumBefore;
    }

    private static List<Integer> extractValuesFromQueries(final List<Query> queries, final int type) {
        final List<Integer> list = queries.stream()
            .filter(query -> query.t == type)
            .map(query -> query.y)
            .distinct()
            .collect(Collectors.toList());
        list.add(0);
        Collections.sort(list);
        return list;
    }

    private static Map<Integer, Integer> inverse(final List<Integer> list) {
        return IntStream.range(0, list.size())
            .boxed()
            .collect(Collectors.toMap(list::get, Function.identity()));
    }

    private static List<Long> createEmptyList(final int size) {
        return Stream.generate(() -> 0L).limit(size).collect(Collectors.toList());
    }

    private static class Query {
        final int t;
        final int x;
        final int y;

        Query(final int t, final int x, final int y) {
            this.t = t;
            this.x = x;
            this.y = y;
        }
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

        T queryFormer(final int endExclusive) {
            return query(0, endExclusive);
        }

        T queryLatter(final int beginInclusive) {
            return query(beginInclusive, internalArray.length);
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
    