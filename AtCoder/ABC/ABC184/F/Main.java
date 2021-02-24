package AtCoder.ABC.ABC184.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
解説AC
半分全列挙
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int t = scanner.nextInt();
        final List<Long> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .filter(l -> l <= t)
            .collect(Collectors.toList());

        final List<Long> former = list.subList(0, list.size() / 2);
        final List<Long> latter = list.subList(list.size() / 2, list.size());
        final List<Long> formerEnumeration = enumerate(former).stream()
            .filter(l -> l <= t)
            .collect(Collectors.toList());
        final List<Long> latterEnumeration = enumerate(latter).stream()
            .filter(l -> l <= t)
            .sorted()
            .collect(Collectors.toList());
        long max = 0;
        for (final long value : formerEnumeration) {
            final int index = Collections.binarySearch(latterEnumeration, t - value);
            max = Math.max(max, value + latterEnumeration.get(index >= 0 ? index : ~index - 1));
        }
        System.out.println(max);
    }

    private static List<Long> enumerate(final List<Long> list) {
        final List<Long> enumeration = new ArrayList<>((int) Math.pow(2, list.size()));
        enumeration.add(0L);
        for (final long value : list) {
            final int size = enumeration.size();
            for (int i = 0; i < size; i++) {
                enumeration.add(enumeration.get(i) + value);
            }
        }
        return enumeration;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
