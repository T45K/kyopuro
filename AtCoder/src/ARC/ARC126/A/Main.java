package ARC.ARC126.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        final String answer = Stream.generate(() -> {
                final long c2 = scanner.nextLong();
                final long c3 = scanner.nextLong();
                final long c4 = scanner.nextLong();
                final long c33 = c3 / 2;
                if (c33 <= c4) {
                    return c33 + calc4And2(c4 - c33, c2);
                } else {
                    final long c3322 = Math.min(c33 - c4, c2 / 2);
                    return c4 + c3322 + Math.max(0, (c2 - 2 * c3322) / 5);
                }
            })
            .limit(t)
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static long calc4And2(final long c4, final long c2) {
        final long c44 = c4 / 2;
        final long c442 = Math.min(c44, c2);
        if (c4 % 2 == 0) {
            return c442 + Math.max(0, (c2 - c442) / 5);
        } else {
            return c442 + ((c2 - c442) >= 3 ? 1 : 0) + Math.max(0, (c2 - c442 - 3) / 5);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
