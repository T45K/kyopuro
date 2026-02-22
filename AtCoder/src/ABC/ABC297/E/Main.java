package ABC.ABC297.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Long> list = Stream.generate(scanner::nextLong)
            .limit(n)
            .distinct()
            .collect(Collectors.toList());

        final Set<Long> exists = new HashSet<>(list);
        final PriorityQueue<Long> queue = new PriorityQueue<>(list);
        for (int i = 0; i < k - 1; i++) {
            assert !queue.isEmpty();
            final long poll = queue.poll();
            for (final long value : list) {
                if (!exists.contains(poll + value)) {
                    queue.add(poll + value);
                    exists.add(poll + value);
                }
            }
        }

        System.out.println(queue.poll());
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
