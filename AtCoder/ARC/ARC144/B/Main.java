package AtCoder.ARC.ARC144.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        System.out.println(recursive(1, 1_000_000_001, a, b, list));
    }

    private static long recursive(final int begin, final int end, final int a, final int b, final List<Integer> list) {
        if (end - begin <= 1) {
            return begin;
        }

        final int mid = (begin + end) / 2;
        long addCount = 0;
        long subCount = 0;
        for (final long value : list) {
            if (value < mid) {
                addCount += (mid - value + a - 1) / a;
            } else if (value > mid) {
                subCount += (value - mid) / b;
            }
        }
        if (subCount >= addCount) {
            return recursive(mid, end, a, b, list);
        } else {
            return recursive(begin, mid, a, b, list);
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
