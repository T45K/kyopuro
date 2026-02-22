package other.typecal90.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] firstClass = new long[n + 1];
        final long[] secondClass = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            final int c = scanner.nextInt();
            final int p = scanner.nextInt();
            if (c == 1) {
                firstClass[i] = p;
            } else {
                secondClass[i] = p;
            }
        }

        for (int i = 1; i <= n; i++) {
            firstClass[i] += firstClass[i - 1];
            secondClass[i] += secondClass[i - 1];
        }

        final int q = scanner.nextInt();
        final String answer = IntStream.range(0, q)
            .mapToObj(i -> {
                final int l = scanner.nextInt();
                final int r = scanner.nextInt();
                final long sumA = firstClass[r] - firstClass[l - 1];
                final long sumB = secondClass[r] - secondClass[l - 1];
                return sumA + " " + sumB;
            })
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
