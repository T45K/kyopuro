package AtCoder.ABC.ABC249.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Pair> list = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextLong()))
            .limit(n)
            .collect(Collectors.toList());

        long max = Long.MIN_VALUE;
        long sum = 0;
        final SizeLimitedPriorityQueue queue = new SizeLimitedPriorityQueue(k);
        for (int i = list.size() - 1; i >= 0; i--) {
            final Pair pair = list.get(i);
            final int t = pair.t;
            final long y = pair.y;

            if (t == 1) {
                max = Math.max(max, y + sum - queue.sum());
                if (queue.isEmpty()) {
                    System.out.println(max);
                    return;
                }
                queue.sizeDown();
            } else {
                sum += y;
                if (y < 0) {
                    queue.add((int) y);
                }
            }
        }
        max = Math.max(max, sum - queue.sum);

        System.out.println(max);
    }

    private static class SizeLimitedPriorityQueue {
        private final PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        private long sum = 0;

        private int size; // always queue.size <= size

        public SizeLimitedPriorityQueue(final int initialSize) {
            this.size = initialSize;
        }

        long sum() {
            return this.sum;
        }

        void add(final int value) {
            if (queue.size() < size) {
                queue.add(value);
                sum += value;
            } else {
                exchange(value);
            }
        }

        private void exchange(final int value) {
            if (queue.isEmpty()) {
                return;
            }
            final int poll = queue.poll();
            sum -= poll;
            final int min = Math.min(poll, value);
            queue.add(min);
            sum += min;
        }

        void sizeDown() {
            if (queue.isEmpty()) {
                return;
            }
            size--;
            while (queue.size() > size) {
                final int poll = queue.poll();
                sum -= poll;
            }
        }

        boolean isEmpty() {
            return size == 0;
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

    private static class Pair {
        final int t;
        final long y;

        Pair(final int t, final long y) {
            this.t = t;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair pair = (Pair) o;
            return Objects.equals(t, pair.t) && Objects.equals(y, pair.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(t, y);
        }
    }
}
