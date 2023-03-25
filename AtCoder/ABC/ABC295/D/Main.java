package AtCoder.ABC.ABC295.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> upperBoundComparator = (x, y) -> x > y ? 1 : -1;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();

        final int[] binArray = IntStream.range(0, s.length())
            .map(i -> s.charAt(i) - '0')
            .map(v -> 1 << v)
            .toArray();
        final int[] acc = new int[s.length()];
        acc[0] = binArray[0];
        for (int i = 1; i < acc.length; i++) {
            acc[i] = acc[i - 1] ^ binArray[i];
        }
        final Map<Integer, List<Integer>> binIndexesMap = IntStream.range(0, s.length())
            .boxed()
            .collect(Collectors.groupingBy(i -> acc[i]));

        long sum = binIndexesMap.getOrDefault(0, Collections.emptyList()).size();
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            tmp ^= binArray[i];
            final List<Integer> list = binIndexesMap.getOrDefault(tmp, Collections.emptyList());
            if (list.isEmpty()) {
                continue;
            }
            final int index = ~Collections.binarySearch(list, i, upperBoundComparator);
            sum += list.size() - index;
        }
        System.out.println(sum);
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
    }
}
