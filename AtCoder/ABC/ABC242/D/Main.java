package AtCoder.ABC.ABC242.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
一旦最初まで戻す -> 復元する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final int q = scanner.nextInt();

        final String answer = Stream.generate(() -> new Pair<>(scanner.nextLong(), scanner.nextLong() - 1))
            .limit(q)
            .map(pair -> solve(s, pair.first, pair.second))
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));

        System.out.println(answer);
    }

    private static char solve(final String s, final long t, final long k) {
        long rest = k;
        final Deque<Integer> queue = new ArrayDeque<>();
        for (long i = 0; i < t; i++) {
            if (rest == 0) {
                return (char) (reproduce(queue, (int) ((s.charAt(0) - 'A' + t - i) % 3)) + 'A');
            }
            if (rest % 2 == 0) {
                queue.add(1);
            } else {
                queue.add(2);
            }
            rest /= 2;
        }

        return (char) (reproduce(queue, s.charAt((int) rest) - 'A') + 'A');
    }

    private static int reproduce(final Deque<Integer> queue, final int v) {
        if (queue.isEmpty()) {
            return v;
        }
        final int last = queue.pollLast();
        return reproduce(queue, (v + last) % 3);
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

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
