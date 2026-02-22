package ABC.ABC173.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
降順にソートしてどんどん挟んでいくイメージ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        final Deque<Long> queue = new ArrayDeque<>();
        queue.add(list.get(0));
        queue.add(list.get(1));
        long sum = list.get(0);
        final Deque<Long> tmp = new ArrayDeque<>();
        for (int i = 2; i < n; i++) {
            if (queue.isEmpty()) {
                queue.addAll(tmp);
                tmp.clear();
            }

            final long a = Optional.ofNullable(queue.pollFirst()).orElseThrow();
            final long b = queue.isEmpty() ? list.get(0) : queue.peekFirst();
            sum += Math.min(a, b);
            final long value = list.get(i);
            tmp.add(a);
            tmp.add(value);
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
    