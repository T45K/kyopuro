package AtCoder.other.typecal90.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int[][] table = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                table[i][j] = scanner.nextInt();
            }
        }
        final int[] col = Arrays.stream(table)
            .mapToInt(array -> Arrays.stream(array).sum())
            .toArray();
        final int[] row = IntStream.range(0, w)
            .map(j -> IntStream.range(0, h).map(i -> table[i][j]).sum())
            .toArray();
        final String answer = IntStream.range(0, h)
            .mapToObj(i -> IntStream.range(0, w)
                .map(j -> col[i] + row[j] - table[i][j])
                .mapToObj(Objects::toString)
                .collect(Collectors.joining(" "))
            ).collect(Collectors.joining("\n"));
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
