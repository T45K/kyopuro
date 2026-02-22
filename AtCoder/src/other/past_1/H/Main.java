package other.past_1.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
セグ木で殴る
ちゃんとやるならそれぞれの最小値を持つだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        final List<Long> oddList = IntStream.range(0, n)
                .filter(i -> i % 2 == 0)
                .mapToObj(list::get)
                .collect(Collectors.toList());

        final List<Long> evenList = IntStream.range(0, n)
                .filter(i -> i % 2 == 1)
                .mapToObj(list::get)
                .collect(Collectors.toList());

        final SegmentTree evenTree = new SegmentTree(evenList, Long.MAX_VALUE, Math::min);
        final SegmentTree oddTree = new SegmentTree(oddList, Long.MAX_VALUE, Math::min);

        final long oddSize = oddList.size();
        long evenSold = 0;
        long oddSold = 0;
        long sum = 0;

        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            final int operation = scanner.nextInt();
            switch (operation) {
                case 1: {
                    final int x = scanner.nextInt() - 1;
                    final long a = scanner.nextInt();
                    if (x % 2 == 1) {
                        final long value = evenTree.get(x / 2);
                        if (value - evenSold < a) {
                            break;
                        }
                        evenTree.update(x / 2, value - a);
                    } else {
                        final long value = oddTree.get(x / 2);
                        if (value - oddSold < a) {
                            break;
                        }
                        oddTree.update(x / 2, value - a);
                    }
                    sum += a;
                    break;
                }
                case 2: {
                    final long a = scanner.nextInt();
                    final long min = oddTree.query((int) oddSize);
                    if (min - oddSold < a) {
                        break;
                    }
                    oddSold += a;
                    sum += oddSize * a;
                    break;
                }
                case 3: {
                    final long a = scanner.nextInt();
                    final long min = Math.min(evenTree.query(evenList.size()) - evenSold, oddTree.query((int) oddSize) - oddSold);
                    if (min < a) {
                        break;
                    }
                    sum += (long) n * a;
                    oddSold += a;
                    evenSold += a;
                }
            }
        }
        System.out.println(sum);
    }


    private static class SegmentTree {
        private final long[] internalTree;
        private final int exponent;
        private final long initialValue;
        private final BiFunction<Long, Long, Long> comparator;

        SegmentTree(final List<Long> list, final long initialValue, final BiFunction<Long, Long, Long> comparator) {
            this.exponent = calcExponent(list.size());
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalTree = initTree(list, initialValue);
        }

        long get(final int index) {
            return internalTree[index + exponent];
        }

        void update(final int index, final long value) {
            internalTree[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalTree[current] = comparator.apply(internalTree[current * 2], internalTree[current * 2 + 1]);
                current /= 2;
            }
        }

        long query(final int right) {
            return query(0, right, 0, exponent, 1);
        }

        long query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalTree[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        private long[] initTree(final List<Long> list, final long initialValue) {
            final long[] array = new long[exponent * 2];
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

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
