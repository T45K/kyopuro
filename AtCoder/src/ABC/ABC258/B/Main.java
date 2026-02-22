package ABC.ABC258.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static final int[] X_MOVE = {0, 0, 1, 1, 1, -1, -1, -1};
    private static final int[] Y_MOVE = {1, -1, 0, 1, -1, 0, 1, -1};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            final char[] chars = scanner.next().toCharArray();
            for (int j = 0; j < n; j++) {
                table[i][j] = chars[j] - '0';
            }
        }

        final long answer = IntStream.range(0, n).boxed()
            .flatMap(i -> IntStream.range(0, n).boxed()
                .flatMap(j -> IntStream.range(0, 8)
                    .mapToLong(k -> IntStream.range(0, n)
                        .mapToLong(l -> table[(n + i + X_MOVE[k] * l) % n][(n + j + Y_MOVE[k] * l) % n] * (long) Math.pow(10, n - l - 1))
                        .sum()
                    ).boxed()
                )
            ).max(Long::compare)
            .orElseThrow();
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
