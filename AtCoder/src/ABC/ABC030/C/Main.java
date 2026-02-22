package ABC.ABC030.C;

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
        final int m = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final List<Integer> a = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> b = Stream.generate(scanner::nextInt)
            .limit(m)
            .collect(Collectors.toList());
        final int answer = forth(0, a, b, x, y);
        System.out.println(answer);
    }

    private static int forth(final int time, final List<Integer> a, final List<Integer> b, final int x, final int y) {
        final int index = binarySearch(a, time);
        if (index == a.size()) {
            return 0;
        }
        return back(a.get(index) + x, a, b, x, y);
    }

    private static int back(final int time, final List<Integer> a, final List<Integer> b, final int x, final int y) {
        final int index = binarySearch(b, time);
        if (index == b.size()) {
            return 0;
        }
        return 1 + forth(b.get(index) + y, a, b, x, y);
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
