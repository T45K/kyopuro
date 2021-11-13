package AtCoder.ABC.ABC227.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// TODO
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Long> list = Stream.generate(scanner::nextLong)
            .limit(n)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        final long[] array = new long[k];
        for (int i = 0; i < k; i++) {
            array[i] = list.get(i);
        }
        final PriorityQueue<Integer> queue = IntStream.range(0, k)
            .boxed()
            .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparingLong(i -> array[i]))));
        for (int i = k; i < n; i++) {
            final long value = list.get(i);
            final int index = queue.poll();
            array[index] += value;
            queue.add(index);
        }
        final int index = queue.poll();
        System.out.println(array[index]);
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
