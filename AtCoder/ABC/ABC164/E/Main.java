package AtCoder.ABC.ABC164.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int s = scanner.nextInt();

        final Map<Integer, List<Destination>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            graph.computeIfAbsent(u, value -> new ArrayList<>()).add(new Destination(u, v, a, b));
            graph.computeIfAbsent(v, value -> new ArrayList<>()).add(new Destination(v, u, b, b));
        }

        final City[] cities = new City[n + 1];
        for (int i = 1; i <= n; i++) {
            final int c = scanner.nextInt();
            final int d = scanner.nextInt();
            cities[i] = new City(c, d);
        }

        final long[] answers = new long[n + 1];
        Arrays.fill(answers, -1);
        answers[1] = 0;
        final PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingLong(pair -> pair.destination.time));
        queue.addAll(graph.get(1).stream().map(dest -> new Pair(s - dest.cost, dest)).collect(Collectors.toList()));
        final City tmp = cities[1];
        for (int i = 1; s + tmp.rate * i <= 2500; i++) {
            queue.add(new Pair(s + i * tmp.rate, new Destination(1, 1, 0, i * tmp.time)));
        }
        while (!queue.isEmpty()) {
            final Pair poll = queue.poll();
            if (poll.money < 0) {
                continue;
            }
        }
    }

    private static boolean isFilled(final long[] answers) {
        return IntStream.range(1, answers.length)
                .allMatch(i -> answers[i] >= 0);
    }

    private static class Pair {
        final long money;
        final Destination destination;

        Pair(final long money, final Destination destination) {
            this.money = money;
            this.destination = destination;
        }
    }

    private static class Destination {
        final int from;
        final int to;
        final long cost;
        final long time;

        Destination(final int from, final int to, final long cost, final long time) {
            this.from = from;
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    private static class City {
        final long rate;
        final long time;

        City(final long rate, final long time) {
            this.rate = rate;
            this.time = time;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
    