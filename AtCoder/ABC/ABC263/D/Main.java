package AtCoder.ABC.ABC263.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long l = scanner.nextLong();
        final long r = scanner.nextLong();
        final long[] array = Stream.generate(scanner::nextLong)
            .mapToLong(Long::longValue)
            .limit(n)
            .toArray();

        final long[][] naturalDp = new long[2][n]; // 0: not use L, 1: use L
        naturalDp[0][0] = array[0];
        naturalDp[1][0] = l;
        for (int i = 1; i < n; i++) {
            naturalDp[0][i] = Math.min(naturalDp[0][i - 1], naturalDp[1][i - 1]) + array[i];
            naturalDp[1][i] = naturalDp[1][i - 1] + l;
        }

        final long[][] reverseDp = new long[2][n];
        reverseDp[0][n - 1] = array[n - 1];
        reverseDp[1][n - 1] = r;
        for (int i = n - 2; i >= 0; i--) {
            reverseDp[0][i] = Math.min(reverseDp[0][i + 1], reverseDp[1][i + 1]) + array[i];
            reverseDp[1][i] = reverseDp[1][i + 1] + r;
        }

        final long answer = concat(
            LongStream.of(Math.min(reverseDp[0][0], reverseDp[1][0])),
            IntStream.range(1, n - 1).mapToLong(i -> Math.min(naturalDp[0][i], naturalDp[1][i]) + Math.min(reverseDp[0][i + 1], reverseDp[1][i + 1])),
            LongStream.of(Math.min(naturalDp[0][n - 1], naturalDp[1][n - 1]))
        ).min()
            .orElseThrow();
        System.out.println(answer);
    }

    private static LongStream concat(final LongStream... streams) {
        if (streams.length == 0) {
            return LongStream.empty();
        } else {
            return LongStream.concat(streams[0], concat(Arrays.copyOfRange(streams, 1, streams.length)));
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
