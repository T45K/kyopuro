package AtCoder.ABC.ABC021.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, List<Point>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();

            graph.computeIfAbsent(x, v -> new ArrayList<>()).add(new Point(x, y));
            graph.computeIfAbsent(y, v -> new ArrayList<>()).add(new Point(y, x));
        }

        final int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[a] = 0;
        final long[] pattern = new long[n + 1];
        pattern[a] = 1;
        final PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));
        queue.addAll(graph.get(a).stream().peek(p -> p.distance = 1).collect(Collectors.toList()));
        while (!queue.isEmpty()) {
            final Point point = queue.poll();
            if (distance[point.to] > point.distance) {
                distance[point.to] = point.distance;
                pattern[point.to] = pattern[point.from];
                queue.addAll(graph.get(point.to).stream().peek(p -> p.distance = distance[point.to] + 1).collect(Collectors.toList()));
            } else if (distance[point.to] == distance[point.from] + 1) {
                pattern[point.to] = (pattern[point.to] + pattern[point.from]) % MOD;
            }
        }

        System.out.println(pattern[b]);
    }

    static class Point {
        final int from;
        final int to;
        int distance;

        Point(final int from, final int to) {
            this.from = from;
            this.to = to;
        }
    }
}
