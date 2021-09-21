package AtCoder.ARC.ARC126.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
解説AC
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final int max = Arrays.stream(array).max().orElseThrow();
        final long[] count = new long[2 * max];
        final long[] sum = new long[2 * max];
        for (final int value : array) {
            count[value]++;
            sum[value] += value;
        }
        accumulate(count);
        accumulate(sum);

        final int maxGcd = IntStream.rangeClosed(1, max)
            .filter(x -> IntStream.rangeClosed(1, (max + x - 1) / x)
                .mapToLong(i -> x * i * (count[i * x] - count[(i - 1) * x]) - (sum[i * x] - sum[(i - 1) * x]))
                .sum() <= k)
            .max()
            .orElse(1);

        final long neededToMakeAllMax = (long) max * n - sum[max];
        if (neededToMakeAllMax <= k) {
            System.out.println(max + (k - neededToMakeAllMax) / n);
        } else {
            System.out.println(maxGcd);
        }
    }

    private static void accumulate(final long[] array) {
        for (int i = 1; i < array.length; i++) {
            array[i] = array[i] + array[i - 1];
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
