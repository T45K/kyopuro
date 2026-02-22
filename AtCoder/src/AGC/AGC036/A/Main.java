package AGC.AGC036.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final long BASE = 1_000_000_000;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long s = scanner.nextLong();
        if (s <= BASE) {
            final long diff = BASE - s;
            System.out.println(format(0, 0, BASE, 1, diff, 1));
            return;
        } else if (s == BASE * BASE) {
            System.out.println(format(0, 0, BASE, 0, 0, BASE));
            return;
        }

        final long y3 = s / BASE + 1;
        final long diff = y3 * BASE - s;
        System.out.println(format(0, 0, BASE, 1, diff, y3));
    }

    private static String format(final long x1, final long y1, final long x2, final long y2, final long x3, final long y3) {
        return Stream.of(x1, y1, x2, y2, x3, y3)
                .map(l -> Long.toString(l))
                .collect(Collectors.joining(" "));

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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
    