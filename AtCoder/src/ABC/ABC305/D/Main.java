package ABC.ABC305.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final int q = scanner.nextInt();
        final long[] sum = new long[n]; // even -> 起きた
        for (int i = 2; i < n; i += 2) {
            sum[i] = list.get(i) - list.get(i - 1) + sum[i - 2];
        }

        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();

            final int beginIndex = (~Collections.binarySearch(list, l, lowerBoundComparator));

            final int add = beginIndex % 2 == 0 ? (list.get(beginIndex) - l) : 0;

            final int endIndex = (~Collections.binarySearch(list, r, lowerBoundComparator));

            final int sub = endIndex % 2 == 0 ? (list.get(endIndex) - r) : 0;

            final int firstWakeUpIndexAfterL = beginIndex / 2 * 2;
            final int lastWakeUpIndexBeforeR = endIndex / 2 * 2;
            joiner.add(Long.toString(sum[lastWakeUpIndexBeforeR] - sum[firstWakeUpIndexAfterL] + add - sub));
        }
        System.out.println(joiner);
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
