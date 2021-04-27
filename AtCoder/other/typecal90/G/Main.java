package AtCoder.other.typecal90.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .sorted()
            .collect(Collectors.toList());
        final int q = scanner.nextInt();
        final String answer = Stream.generate(scanner::nextInt)
            .limit(q)
            .map(b -> {
                final int index = binarySearch(list, b);
                final int bigger = (index < n) ? list.get(index) : Integer.MAX_VALUE;
                final int smaller = (index > 0) ? list.get(index - 1) : Integer.MAX_VALUE;
                return Integer.toString(Math.min(Math.abs(b - bigger), Math.abs(b - smaller)));
            })
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static int binarySearch(final List<Integer> list, final int value) {
        final int index = Collections.binarySearch(list, value);
        return index >= 0 ? index : ~index;
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
