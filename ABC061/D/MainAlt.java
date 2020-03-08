package ABC061.D;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainAlt {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();

            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(new Edge(a, b, c));
        }

        final Deque<Edge> queue = new ArrayDeque<>(graph.get(1));
        final long[] array = new long[n + 1];
        Arrays.fill(array, Long.MIN_VALUE);
        array[1] = 0;

        for (int i = 0; i < n * n; i++) {
            if (queue.isEmpty()) {
                System.out.println(array[n]);
                return;
            }

            final Edge edge = queue.pollFirst();
            if (array[edge.from] + edge.weight <= array[edge.to]) {
                continue;
            }
            array[edge.to] = array[edge.from] + edge.weight;
            final List<Edge> next = graph.get(edge.to);
            if (next != null) {
                queue.addAll(next);
            }
        }

        final long answer = array[n];

        final List<Edge> edges = graph.values().stream().flatMap(MainAlt::flatten).collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            for (final Edge edge : edges) {
                if (array[edge.from] + edge.weight > array[edge.to]) {
                    array[edge.to] = array[edge.from] + edge.weight;
                }
            }
        }

        if (array[n] > answer) {
            System.out.println("inf");
        } else {
            System.out.println(array[n]);
        }
    }

    private static <T> Stream<T> flatten(final List<T> list) {
        return list.stream();
    }

    static class Edge {
        int from;
        int to;
        long weight;

        Edge(final int from, final int to, final long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
