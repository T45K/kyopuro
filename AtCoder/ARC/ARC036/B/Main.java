package AtCoder.ARC.ARC036.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final int[] fromLeft = new int[n];
        for (int i = 1; i < n; i++) {
            if (list.get(i) > list.get(i - 1)) {
                fromLeft[i] = fromLeft[i - 1] + 1;
            } else {
                fromLeft[i] = 0;
            }
        }

        final int[] fromRight = new int[n];
        for (int i = n - 1; i > 0; i--) {
            if (list.get(i - 1) > list.get(i)) {
                fromRight[i - 1] = fromRight[i] + 1;
            } else {
                fromRight[i - 1] = 0;
            }
        }

        IntStream.range(0, n)
            .map(i -> fromLeft[i] + fromRight[i] + 1)
            .max()
            .ifPresent(System.out::println);
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
    