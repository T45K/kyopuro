package AtCoder.other.typecal90.bk063;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.Function;
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

        final Function<Integer, int[]> convert = bit -> IntStream.range(0, h)
            .filter(k -> (bit & (1 << k)) > 0)
            .toArray();

        final BiFunction<int[], Integer, Optional<Integer>> calcCol = (array, j) -> {
            final IntSummaryStatistics summary = Arrays.stream(array)
                .map(i -> table[i][j])
                .distinct()
                .summaryStatistics();
            return summary.getCount() == 1 ? Optional.of(summary.getMin()) : Optional.empty();
        };

        final long answer = IntStream.range(1, 1 << h)
            .mapToObj(convert::apply)
            .mapToLong(array -> array.length * IntStream.range(0, w)
                .mapToObj(j -> calcCol.apply(array, j))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .max(Long::compare)
                .orElse(0L)
            )
            .max()
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
