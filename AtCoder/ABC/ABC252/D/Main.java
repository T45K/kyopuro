package AtCoder.ABC.ABC252.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] values = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Comparator.comparingInt(Map.Entry::getKey))
            .map(Map.Entry::getValue)
            .mapToLong(Long::longValue)
            .toArray();

        final int m = values.length;
        if (m < 3) {
            System.out.println(0);
            return;
        }

        final long[] firstAccumulation = new long[m];
        firstAccumulation[0] = values[0];
        for (int i = 1; i < m; i++) {
            firstAccumulation[i] = firstAccumulation[i - 1] + values[i];
        }
        final long[] product = new long[m];
        product[0] = values[0];
        for (int i = 1; i < m; i++) {
            product[i] = firstAccumulation[i - 1] * values[i];
        }
        final long[] secondAccumulation = new long[m];
        secondAccumulation[1] = product[1];
        for (int i = 2; i < m; i++) {
            secondAccumulation[i] = secondAccumulation[i - 1] + product[i];
        }

        final long answer = Stream.iterate(m - 1, i -> i >= 2, i -> i - 1)
            .mapToLong(i -> values[i] * secondAccumulation[i - 1])
            .sum();
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
