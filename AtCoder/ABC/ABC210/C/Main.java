package AtCoder.ABC.ABC210.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < k; i++) {
            final int value = list.get(i);
            counter.compute(value, (key, v) -> v == null ? 1 : v + 1);
        }
        int size = counter.size();
        int max = size;
        for (int i = k; i < n; i++) {
            final int in = list.get(i);
            final int out = list.get(i - k);
            if (!counter.containsKey(in) || counter.get(in) == 0) {
                size++;
            }
            counter.compute(in, (key, value) -> value == null ? 1 : value + 1);
            counter.compute(out, (key, value) -> value - 1);
            if (counter.get(out) == 0) {
                size--;
            }
            max = Math.max(max, size);
        }
        System.out.println(max);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
