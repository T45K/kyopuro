package ABC051.D;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[][] graph = new int[n + 1][n + 1];
        for (final int[] array : graph) {
            Arrays.fill(array, Integer.MAX_VALUE / 2);
        }
        final List<Edge> edges = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            final int from = scanner.nextInt();
            final int to = scanner.nextInt();
            final int cost = scanner.nextInt();

            edges.add(new Edge(from, to, cost));
            graph[from][to] = cost;
            graph[to][from] = cost;
        }

        for (int k = 0; k < n + 1; k++) {
            for (int i = 0; i < n + 1; i++) {
                for (int j = 0; j < n + 1; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        final long answer = edges.stream()
                .filter(edge -> edge.cost > graph[edge.from][edge.to])
                .count();

        System.out.println(answer);
    }

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(final int from, final int to, final int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
