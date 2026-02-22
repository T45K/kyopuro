package ABC.ABC240.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
総和を考える
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        final String answer = Stream.generate(() -> {
                final long n = scanner.nextInt();
                scanner.nextInt();
                long sum = 0;
                long prev = 0;
                long max = Long.MIN_VALUE;
                for (int i = 0; i < n; i++) {
                    final long x = scanner.nextInt();
                    final long y = scanner.nextInt();
                    if (prev >= 0 && prev + x * y < 0) {
                        final long div = Math.max(prev / -(x), 1); // Bが0になるまで
                        max = Math.max(max, sum + x * div * (div + 1) / 2 + prev * div);
                    }
                    sum += x * y * (y + 1) / 2 + prev * y;
                    max = Math.max(max, sum);
                    prev = prev + x * y;
                }
                return max;
            }).limit(t)
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
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
