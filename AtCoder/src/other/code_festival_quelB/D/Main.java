package other.code_festival_quelB.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列系
各高さに対する今見ている地点から最も近い点をセグ木で持つ
 */
public class Main {
    private static final Deque<Integer> DUMMY_QUEUE = new ArrayDeque<>();

    public static void main(final String[] args) {
        DUMMY_QUEUE.add(Integer.MAX_VALUE);
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());

        final List<Integer> initialLeft = IntStream.rangeClosed(0, 100_000)
            .mapToObj(i -> 0)
            .collect(Collectors.toList());
        final SegmentTree<Integer> left = new SegmentTree<>(initialLeft, 0, Math::max);

        final Map<Integer, Deque<Integer>> queues = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            final int value = list.get(i);
            queues.computeIfAbsent(value, v -> new ArrayDeque<>()).add(i);
        }

        final List<Integer> initialRight = IntStream.rangeClosed(0, 100_000)
            .mapToObj(i -> Optional.ofNullable(queues.get(i)).orElse(DUMMY_QUEUE).peekFirst())
            .collect(Collectors.toList());
        final SegmentTree<Integer> right = new SegmentTree<>(initialRight, Integer.MAX_VALUE, Math::min);

        for (int i = 0; i < list.size(); i++) {
            final int height = list.get(i);
            final int nearestLeft = left.query(height + 1);
            final int queryResult2 = right.query(height + 1);
            final int nearestRight = queryResult2 == Integer.MAX_VALUE ? n : queryResult2;
            System.out.println((i - nearestLeft) + (nearestRight - i - 1));
            left.update(height, i + 1);
            final Deque<Integer> queue = queues.get(height);
            queue.pollFirst();
            right.update(height, Optional.ofNullable(queue.peekFirst()).orElse(Integer.MAX_VALUE));
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

        T query(final int left) {
            return query(left, 100001, 0, exponent, 1);
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
