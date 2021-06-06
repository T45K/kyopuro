package AtCoder.ABC.ABC204.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Destination>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            final int d = scanner.nextInt();
            map.computeIfAbsent(a, k -> new ArrayList<>()).add(new Destination(b, c, d));
            map.computeIfAbsent(b, k -> new ArrayList<>()).add(new Destination(a, c, d));
        }

        if (!isReachable(map, n)) {
            System.out.println(-1);
            return;
        }

        System.out.println(dijkstra(map, n));
    }

    private static long dijkstra(final Map<Integer, List<Destination>> map, final int n) {
        final long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE / 2);
        distance[1] = 0;
        final PriorityQueue<To> queue = new PriorityQueue<>(Comparator.comparingLong(to -> to.cost));
        for (final Destination next : get(map, 1)) {
            if (next.to == 1) {
                continue;
            }
            final long cost = calcCost(next.base, next.coefficient, 0);
            queue.add(new To(next.to, cost));
            distance[next.to] = cost;
        }
        while (!queue.isEmpty()) {
            final To poll = queue.poll();
            if (poll.to == n) {
                return poll.cost;
            }
            if (poll.cost > distance[poll.to]) {
                continue;
            }
            distance[poll.to] = poll.cost;
            for (final Destination next : get(map, poll.to)) {
                if (next.to == poll.to) {
                    continue;
                }
                final long cost = calcCost(next.base, next.coefficient, poll.cost);
                if (distance[next.to] <= cost) {
                    continue;
                }
                queue.add(new To(next.to, cost));
                distance[next.to] = cost;
            }
        }
        return distance[n];
    }

    private static long calcCost(final long base, final long coefficient, final long currentTime) {
        final int sqrt = (int) Math.sqrt(coefficient);
        if (currentTime > sqrt) {
            return base + currentTime + coefficient / (currentTime + 1);
        } else {
            return base + sqrt + coefficient / (sqrt + 1);
        }
    }

    private static boolean isReachable(final Map<Integer, List<Destination>> map, final int n) {
        final boolean[] array = new boolean[n + 1];
        final Consumer<Integer> consumer = new Consumer<>() {
            @Override
            public void accept(final Integer current) {
                for (final Destination destination : get(map, current)) {
                    if (array[destination.to]) {
                        continue;
                    }
                    array[destination.to] = true;
                    accept(destination.to);
                }
            }
        };
        consumer.accept(1);
        return array[n];
    }

    private static <T> List<T> get(final Map<Integer, List<T>> map, final int key) {
        return Optional.ofNullable(map.get(key)).orElse(Collections.emptyList());
    }

    private static class To {
        final int to;
        final long cost;

        To(final int to, final long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class Destination {
        final int to;
        final int base;
        final int coefficient;

        Destination(final int to, final int base, final int coefficient) {
            this.to = to;
            this.base = base;
            this.coefficient = coefficient;
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
