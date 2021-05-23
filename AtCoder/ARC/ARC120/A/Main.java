package AtCoder.ARC.ARC120.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final long[] sum = new long[n];
        final long[] sumSum = new long[n];
        final long[] max = new long[n];
        sum[0] = array[0];
        max[0] = array[0];
        for (int i = 1; i < n; i++) {
            sum[i] = array[i] + sum[i - 1];
            max[i] = Math.max(array[i], max[i - 1]);
            sumSum[i] += sum[i - 1] + sumSum[i - 1];
        }

        final String answer = IntStream.range(0, n)
            .mapToLong(i -> sum[i] + max[i] * (i + 1) + sumSum[i])
            .mapToObj(Long::toString)
            .collect(Collectors.joining(System.lineSeparator()));
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
