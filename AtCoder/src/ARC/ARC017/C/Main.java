package ARC.ARC017.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
半分全探索
全探索が難しかったら
1. 分割して全探索
2. 結果を良い感じに組み合わせる(今回はソートして二分探索)
 */
public class Main {
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> upperBoundComparator = (x, y) -> x > y ? 1 : -1;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final List<Integer> formerList = calc(list, 0, n / 2);
        final List<Integer> latterList = calc(list, n / 2, n);

        Collections.sort(latterList);
        int counter = 0;
        for (final int value : formerList) {
            if (value > x) {
                continue;
            }

            final int start = Collections.binarySearch(latterList, x - value, upperBoundComparator);
            final int end = Collections.binarySearch(latterList, x - value, lowerBoundComparator);
            counter += end - start;
        }

        System.out.println(counter);
    }

    private static List<Integer> calc(final List<Integer> list, final int start, final int end) {
        final List<Integer> sums = new ArrayList<>();
        sums.add(0);
        for (int i = start; i < end; i++) {
            final int current = list.get(i);
            final List<Integer> tmp = new ArrayList<>();
            for (final int value : sums) {
                tmp.add(value + current);
            }
            sums.addAll(tmp);
        }
        return sums;
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
    