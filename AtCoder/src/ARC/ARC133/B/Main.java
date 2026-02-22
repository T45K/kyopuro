package ARC.ARC133.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
高速LCSで殴る
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> p = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> q = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        System.out.println(calcLCSLength(p, q, n));
    }

    private static int calcLCSLength(final List<Integer> p, final List<Integer> q, final int n) {
        final Map<Integer, Integer> indexOfQ = IntStream.range(0, q.size())
            .boxed()
            .collect(Collectors.toMap(q::get, Function.identity()));

        final Integer[] lcs = new Integer[p.size() + 1];
        Arrays.fill(lcs, Integer.MAX_VALUE);
        lcs[0] = -1;

        @SuppressWarnings("ComparatorMethodParameterNotUsed") final Comparator<Integer> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;
        for (final int c : p) {
            final List<Integer> multiples = Stream.iterate(c, i -> i <= n, i -> i + c)
                .map(indexOfQ::get)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            for (final int multiple : multiples) {
                final int index = ~Arrays.binarySearch(lcs, multiple, lowerBoundComparator);
                lcs[index] = multiple;
            }
        }

        return ~Arrays.binarySearch(lcs, Integer.MAX_VALUE - 1) - 1;
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
