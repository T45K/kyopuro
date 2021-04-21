package AtCoder.ABC.ABC188.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int c = scanner.nextInt();
        final Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c2 = scanner.nextInt();
            map.compute(a, (k, v) -> v == null ? c2 : v + c2);
            map.compute(b + 1, (k, v) -> v == null ? -c2 : v - c2);
        }
        final int[] keys = map.keySet().stream()
            .mapToInt(Integer::intValue)
            .sorted()
            .toArray();
        final long[] values = Arrays.stream(keys)
            .mapToLong(map::get)
            .toArray();
        for (int i = 1; i < values.length; i++) {
            values[i] += values[i - 1];
        }
        final long sum = IntStream.range(0, keys.length - 1)
            .mapToLong(i -> Math.min(values[i], c) * (keys[i + 1] - keys[i]))
            .sum();
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
