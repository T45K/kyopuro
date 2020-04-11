package AtCoder.ABC.ABC061.D;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();

            edges[i] = new Edge(a, b, -c);
        }

        final long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (final Edge edge : edges) {
                if (dist[edge.from] != Long.MAX_VALUE && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        final long answer = -dist[n];
        final boolean[] isClosed = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            for (final Edge edge : edges) {
                if (isClosed[edge.from]) {
                    isClosed[edge.to] = true;
                }
                if (dist[edge.from] != Long.MAX_VALUE && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    isClosed[edge.to] = true;
                }
            }
        }

        if (isClosed[n]) {
            System.out.println("inf");
        } else {
            System.out.println(answer);
        }
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
