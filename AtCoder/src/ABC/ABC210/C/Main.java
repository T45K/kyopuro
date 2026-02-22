package ABC.ABC210.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
尺取り
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final Counter counter = new Counter();
        for (int i = 0; i < k; i++) {
            final int value = list.get(i);
            counter.increment(value);
        }
        int size = counter.size();
        int max = size;
        for (int i = k; i < n; i++) {
            final int in = list.get(i);
            final int out = list.get(i - k);
            if (counter.isZero(in)) {
                size++;
            }
            counter.increment(in);
            counter.decrement(out);
            if (counter.isZero(out)) {
                size--;
            }
            max = Math.max(max, size);
        }
        System.out.println(max);
    }

    private static class Counter extends HashMap<Integer, Integer> {
        boolean isZero(final int key) {
            return this.get(key) == null || this.get(key) == 0;
        }

        void increment(final int key) {
            this.compute(key, (k, v) -> v == null ? 1 : v + 1);
        }

        void decrement(final int key) {
            this.compute(key, (k, v) -> Optional.ofNullable(v).orElseThrow() - 1);
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
    }
}
