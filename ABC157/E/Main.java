package ABC157.E;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> 1L << (s.charAt(i) - 'a'))
                .collect(Collectors.toList());

        final SegmentTree tree = new SegmentTree(list, 0, (a, b) -> a | b);
        final int q = scanner.nextInt();
        for (int _ = 0; _ < q; _++) {
            if (scanner.nextInt() == 1) {
                final int i = scanner.nextInt();
                final int c = scanner.next().toCharArray()[0] - 'a';
                tree.update(i - 1, 1 << c);
            } else {
                final int l = scanner.nextInt();
                final int r = scanner.nextInt();
                final long result = tree.query(l - 1, r);
                final String tmp = Long.toBinaryString(result);
                final long answer = IntStream.range(0, tmp.length())
                        .map(tmp::charAt)
                        .filter(c -> c == '1')
                        .count();

                System.out.println(answer);
            }
        }
    }

    private static class SegmentTree {
        private final long[] internalTree;
        private final int exponent;
        private final long initialValue;
        private BiFunction<Long, Long, Long> updater;

        SegmentTree(final List<Long> list, final long initialValue, final BiFunction<Long, Long, Long> updater) {
            this.exponent = calcExponent(list.size());
            this.updater = updater;
            this.initialValue = initialValue;
            internalTree = initTree(list, initialValue);
        }

        private long[] initTree(final List<Long> list, final long initialValue) {
            final long[] array = new long[exponent * 2];
            Arrays.fill(array, initialValue);
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = list.get(i);
            }

            for (int i = exponent - 1; i > 0; i--) {
                array[i] = updater.apply(array[i * 2], array[i * 2 + 1]);
            }

            return array;
        }

        void update(final int index, final long value) {
            internalTree[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalTree[current] = updater.apply(internalTree[current * 2], internalTree[current * 2 + 1]);
                current /= 2;
            }
        }

        long query(final int left, final int right) {
            return query(left, right, 0, exponent, 1);
        }

        long query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalTree[k];
            }

            final int mid = (begin + end) / 2;
            return updater.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        private int calcExponent(final int n) {
            int exp = 1;
            while (exp < n) {
                exp <<= 1;
            }
            return exp;
        }
    }
}
