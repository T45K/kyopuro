package ABC.ABC271.B;

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
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final int[][] arrays = new int[n][];
        for (int i = 0; i < n; i++) {
            final int l = scanner.nextInt();
            arrays[i] = Stream.generate(scanner::nextInt)
                .limit(l)
                .mapToInt(Integer::intValue)
                .toArray();
        }

        final String answer = Stream.generate(() -> {
                final int s = scanner.nextInt();
                final int t = scanner.nextInt();
                return arrays[s - 1][t - 1];
            }).limit(q)
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
