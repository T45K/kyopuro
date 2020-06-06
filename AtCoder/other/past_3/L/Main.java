package AtCoder.other.past_3.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
インデックス付きセグ木で殴る
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<IndexValue> firstList = new ArrayList<>();
        final List<IndexValue> secondList = new ArrayList<>();
        final ArrayDeque<Integer>[] queues = new ArrayDeque[n];
        for (int i = 0; i < n; i++) {
            final int k = scanner.nextInt();
            firstList.add(new IndexValue(i, scanner.nextInt()));
            if (k >= 2) {
                secondList.add(new IndexValue(i, scanner.nextInt()));
                queues[i] = IntStream.range(2, k)
                    .mapToObj(j -> scanner.nextInt())
                    .collect(Collectors.toCollection(ArrayDeque::new));
            } else {
                secondList.add(new IndexValue(i, 0));
                queues[i] = new ArrayDeque<>();
            }
        }

        final SegmentTree firstTree = new SegmentTree(firstList, 0);
        final SegmentTree secondTree = new SegmentTree(secondList, 0);

        final int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            if (a == 1) {
                final IndexValue indexValue = firstTree.query(n);
                System.out.println(indexValue.value);
                final int index = indexValue.index;
                firstTree.update(index, secondTree.get(index));

                final ArrayDeque<Integer> queue = queues[index];
                if (!queue.isEmpty()) {
                    final int poll = queue.pollFirst();
                    secondTree.update(index, new IndexValue(index, poll));
                } else {
                    secondTree.update(index, new IndexValue(index, 0));
                }
            } else {
                final IndexValue firstIndexValue = firstTree.query(n);
                final IndexValue secondIndexValue = secondTree.query(n);

                if (firstIndexValue.value > secondIndexValue.value) {
                    System.out.println(firstIndexValue.value);
                    final int index = firstIndexValue.index;
                    firstTree.update(index, secondTree.get(index));

                    final ArrayDeque<Integer> queue = queues[index];
                    if (!queue.isEmpty()) {
                        final int poll = queue.pollFirst();
                        secondTree.update(index, new IndexValue(index, poll));
                    } else {
                        secondTree.update(index, new IndexValue(index, 0));
                    }
                } else {
                    System.out.println(secondIndexValue.value);
                    final int index = secondIndexValue.index;
                    final ArrayDeque<Integer> queue = queues[index];
                    if (!queue.isEmpty()) {
                        final int poll = queue.poll();
                        secondTree.update(index, new IndexValue(index, poll));
                    } else {
                        secondTree.update(index, new IndexValue(index, 0));
                    }
                }
            }
        }
    }

    private static class SegmentTree {
        private final IndexValue[] internalTree;
        private final int exponent;
        private final int initialValue;

        SegmentTree(final List<IndexValue> list, final int initialValue) {
            this.exponent = calcExponent(list.size());
            this.initialValue = initialValue;
            internalTree = initTree(list, initialValue);
        }

        IndexValue get(final int index) {
            return internalTree[exponent + index];
        }

        void update(final int index, final IndexValue value) {
            internalTree[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                if (internalTree[current * 2].value > internalTree[current * 2 + 1].value) {
                    internalTree[current] = internalTree[current * 2];
                } else {
                    internalTree[current] = internalTree[current * 2 + 1];
                }
                current /= 2;
            }
        }

        IndexValue query(final int right) {
            return query(0, right, 0, exponent, 1);
        }

        IndexValue query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return new IndexValue(-1, initialValue);
            }

            if (left <= begin && end <= right) {
                return internalTree[k];
            }

            final int mid = (begin + end) / 2;
            final IndexValue a = query(left, right, begin, mid, k * 2);
            final IndexValue b = query(left, right, mid, end, k * 2 + 1);
            if (a.value > b.value) {
                return a;
            } else {
                return b;
            }
        }

        private IndexValue[] initTree(final List<IndexValue> list, final int initialValue) {
            final IndexValue[] array = new IndexValue[exponent * 2];
            Arrays.fill(array, new IndexValue(-1, initialValue));
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = list.get(i);
            }

            for (int i = exponent - 1; i > 0; i--) {
                if (array[i * 2].value > array[i * 2 + 1].value) {
                    array[i] = array[i * 2];
                } else {
                    array[i] = array[i * 2 + 1];
                }
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

    private static class IndexValue {
        final int index;
        final int value;

        IndexValue(final int index, final int value) {
            this.index = index;
            this.value = value;
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
