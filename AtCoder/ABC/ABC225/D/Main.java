package AtCoder.ABC.ABC225.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final Train[] trains = new Train[n + 1];
        for (int i = 1; i <= n; i++) {
            trains[i] = new Train();
        }
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int query = scanner.nextInt();
            switch (query) {
                case 1: {
                    final int x = scanner.nextInt();
                    final int y = scanner.nextInt();
                    trains[x].next = y;
                    trains[y].previous = x;
                    break;
                }
                case 2: {
                    final int x = scanner.nextInt();
                    final int y = scanner.nextInt();
                    trains[x].next = 0;
                    trains[y].previous = 0;
                    break;
                }
                case 3: {
                    final int x = scanner.nextInt();
                    final Deque<Integer> queue = new ArrayDeque<>();
                    queue.add(x);
                    followPrev(queue, trains, x);
                    followNext(queue, trains, x);
                    final String answer = queue.stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining(" "));
                    joiner.add(queue.size() + " " + answer);
                }
            }
        }
        System.out.println(joiner);
    }

    private static void followPrev(final Deque<Integer> queue, final Train[] trains, final int current) {
        final int previous = trains[current].previous;
        if (previous == 0) {
            return;
        }

        queue.addFirst(previous);
        followPrev(queue, trains, previous);
    }

    private static void followNext(final Deque<Integer> queue, final Train[] trains, final int current) {
        final int next = trains[current].next;
        if (next == 0) {
            return;
        }

        queue.addLast(next);
        followNext(queue, trains, next);
    }

    private static class Train {
        int previous;
        int next;
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    