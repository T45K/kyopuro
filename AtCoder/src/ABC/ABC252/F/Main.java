package ABC.ABC252.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
ちぎった後のパンから組み立てていくという発想
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long l = scanner.nextLong();
        final PriorityQueue<Long> queue = Stream.generate(scanner::nextLong)
            .limit(n)
            .collect(Collectors.toCollection(PriorityQueue::new));

        final long allRangeOfBread = queue.stream().mapToLong(Long::longValue).sum();
        if (allRangeOfBread < l) {
            queue.add(l - allRangeOfBread);
        }

        long sum = 0;
        while (queue.size() >= 2) {
            final long min1 = queue.poll();
            final long min2 = queue.poll();
            sum += min1 + min2;
            queue.add(min1 + min2);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
