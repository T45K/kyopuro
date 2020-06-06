package AtCoder.other.past_3.M;

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
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();

            graph.computeIfAbsent(u, value -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, value -> new ArrayList<>()).add(u);
        }

        final int s = scanner.nextInt();
        final int[] base = dijkstra(graph, n, s);

        final int k = scanner.nextInt();
        final List<Integer> numbers = IntStream.range(0, k)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final List<int[]> dijkstraList = numbers.stream()
            .map(i -> dijkstra(graph, n, i))
            .collect(Collectors.toList());
    }

    private static int[] dijkstra(final Map<Integer, List<Integer>> graph, final int n, final int start) {
        final int[] array = new int[n + 1];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[start] = 0;

        final PriorityQueue<Destination> queue = new PriorityQueue<>(Comparator.comparingInt(dest -> dest.cost));
        graph.get(start).stream()
            .map(i -> new Destination(i, 1))
            .forEach(queue::add);

        while (!queue.isEmpty()) {
            final Destination poll = queue.poll();
            if (poll.cost >= array[poll.to]) {
                continue;
            }

            array[poll.to] = poll.cost;
            graph.get(poll.to).stream()
                .filter(i -> array[i] > poll.cost + 1)
                .map(i -> new Destination(i, poll.cost + 1))
                .forEach(queue::add);
        }

        return array;
    }

    private static class Destination {
        final int to;
        final int cost;

        Destination(final int to, final int cost) {
            this.to = to;
            this.cost = cost;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
