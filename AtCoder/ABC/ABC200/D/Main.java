package AtCoder.ABC.ABC200.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
DPの操作をする
ある被ったら，その二つの操作列が答え
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Number> list = IntStream.rangeClosed(1, n)
            .mapToObj(i -> new Number(i, scanner.nextInt() % 200))
            .collect(Collectors.toList());
        ArrayDeque<Integer>[] queues = new ArrayDeque[201];
        for (final Number number : list) {
            final ArrayDeque<Integer>[] next = new ArrayDeque[201];
            System.arraycopy(queues, 0, next, 0, queues.length);
            for (int i = 0; i < queues.length; i++) {
                final ArrayDeque<Integer> queue = queues[i];
                if (queue == null) {
                    continue;
                }
                final ArrayDeque<Integer> target = queues[(i + number.value) % 200];
                if (target == null) {
                    final ArrayDeque<Integer> copied = new ArrayDeque<>(queue);
                    copied.add(number.index);
                    next[(i + number.value) % 200] = copied;
                    continue;
                }
                System.out.println("Yes");
                System.out.println(target.size() + " " + target.stream().map(Objects::toString).collect(Collectors.joining(" ")));
                queue.add(number.index);
                System.out.println(queue.size() + " " + queue.stream().map(Objects::toString).collect(Collectors.joining(" ")));
                return;
            }
            if (next[number.value] == null) {
                final ArrayDeque<Integer> deque = new ArrayDeque<>();
                deque.add(number.index);
                next[number.value] = deque;
            } else {
                System.out.println("Yes");
                System.out.println(next[number.value].size() + " " + next[number.value].stream().map(Objects::toString).collect(Collectors.joining(" ")));
                System.out.println(1 + " " + number.index);
                return;
            }
            queues = next;
        }
        System.out.println("No");
    }

    private static class Number {
        final int index;
        final int value;

        Number(final int index, final int value) {
            this.index = index;
            this.value = value;
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
