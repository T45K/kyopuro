package other.typecal90.M;

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

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Node>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new Node(b, c));
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new Node(a, c));
        }
        final long[] natural = dijkstra(graph, 1, n);
        final long[] reverse = dijkstra(graph, n, n);
        final String answer = IntStream.rangeClosed(1, n)
            .mapToLong(i -> natural[i] + reverse[i])
            .mapToObj(Long::toString)
            .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(answer);
    }

    private static long[] dijkstra(final Map<Integer, List<Node>> graph, final int start, final int n) {
        final long[] array = new long[n + 1];
        Arrays.fill(array, Long.MAX_VALUE);
        array[start] = 0;
        final PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingLong(i -> array[i]));
        for (final Node node : graph.get(start)) {
            array[node.dest] = node.cost;
            queue.add(node.dest);
        }
        while (!queue.isEmpty()) {
            final int poll = queue.poll();
            for (final Node node : graph.get(poll)) {
                if (array[poll] + node.cost < array[node.dest]) {
                    array[node.dest] = array[poll] + node.cost;
                    queue.add(node.dest);
                }
            }
        }
        return array;
    }

    private static class Node {
        final int dest;
        final long cost;

        Node(final int dest, final long cost) {
            this.dest = dest;
            this.cost = cost;
        }
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
    