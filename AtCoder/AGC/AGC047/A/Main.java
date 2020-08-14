package AtCoder.AGC.AGC047.A;

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

public class Main {
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;
    private static final int ARRAY_SIZE = 22;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> base = IntStream.range(0, n)
            .mapToObj(i -> Math.round(scanner.nextDouble() * 1_000_000_000L))
            .collect(Collectors.toList());

        final List<Integer> div2List = toDivList(base, 2);
        final List<Integer> div5list = toDivList(base, 5);

        @SuppressWarnings("unchecked") final List<Integer>[] array = new List[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            final int div2 = div2List.get(i);
            final int div5 = div5list.get(i);
            array[div5].add(div2);
        }

        for (int i = ARRAY_SIZE - 2; i >= 0; i--) {
            final List<Integer> tmp = array[i];
            tmp.addAll(array[i + 1]);
            tmp.sort(Comparator.naturalOrder());
        }

        final long answer = IntStream.range(0, n)
            .mapToLong(i -> {
                final int div2 = div2List.get(i);
                final int div5 = div5list.get(i);

                final List<Integer> list = array[Math.max(0, 18 - div5)];
                final int count;
                if (div2 >= 18) {
                    count = list.size();
                } else {
                    final int index = Collections.binarySearch(list, 18 - div2, lowerBoundComparator);
                    count = list.size() - (index > 0 ? index : ~index);
                }

                return (div2 >= 9 && div5 >= 9) ? count - 1 : count;
            }).sum();

        System.out.println(answer / 2);
    }

    private static List<Integer> toDivList(final List<Long> list, final int div) {
        return list.stream()
            .map(l -> countDiv(l, div, 0))
            .collect(Collectors.toList());
    }

    private static int countDiv(final long value, final int div, final int count) {
        if (value % div > 0) {
            return count;
        } else {
            return countDiv(value / div, div, count + 1);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
