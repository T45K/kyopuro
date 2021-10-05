package AtCoder.ARC.ARC119.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
サンプルいじってたら合った
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToLong(Integer::longValue)
            .toArray();
        final long[] accumulatedDiff = new long[n];
        accumulatedDiff[0] = array[0];
        for (int i = 1; i < n; i++) {
            accumulatedDiff[i] = array[i] - accumulatedDiff[i - 1];
        }

        final Map<Long, Long> evenCounts = IntStream.range(0, n)
            .filter(i -> i % 2 == 0)
            .mapToObj(i -> accumulatedDiff[i])
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final Map<Long, Long> oddCounts = IntStream.range(0, n)
            .filter(i -> i % 2 == 1)
            .mapToObj(i -> accumulatedDiff[i])
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evenCounts.compute(accumulatedDiff[i], (k, v) -> v == null ? 0 : v - 1);
                sum += Optional.ofNullable(evenCounts.get(accumulatedDiff[i] - array[i])).orElse(0L);
                sum += Optional.ofNullable(oddCounts.get(array[i] - accumulatedDiff[i])).orElse(0L);
            } else {
                oddCounts.compute(accumulatedDiff[i], (k, v) -> v == null ? 0 : v - 1);
                sum += Optional.ofNullable(oddCounts.get(accumulatedDiff[i] - array[i])).orElse(0L);
                sum += Optional.ofNullable(evenCounts.get(array[i] - accumulatedDiff[i])).orElse(0L);
            }
        }
        System.out.println(sum);
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
