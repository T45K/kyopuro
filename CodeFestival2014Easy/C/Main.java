package CodeFestival2014Easy.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int s = scanner.nextInt();
        final int t = scanner.nextInt();

        final List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            final int d = scanner.nextInt();

            edges.add(new Edge(x, y, d));
            edges.add(new Edge(y, x, d));
        }

        final int[] shortestPathFromS = bellmanFord(edges, s, n);
        final int[] shortestPathFromT = bellmanFord(edges, t, n);

        for (int i = 1; i <= n; i++) {
            if (shortestPathFromS[i] == Integer.MAX_VALUE || shortestPathFromT[i] == Integer.MAX_VALUE) {
                continue;
            }

            if (shortestPathFromS[i] == shortestPathFromT[i]) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    private static int[] bellmanFord(final List<Edge> edges, final int start, final int v) {
        final int[] d = new int[v + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;
        for (int i = 0; i < v; i++) {
            boolean isUpdated = false;
            for (final Edge edge : edges) {
                if (d[edge.from] != Integer.MAX_VALUE && d[edge.to] > d[edge.from] + edge.cost) {
                    isUpdated = true;
                    d[edge.to] = d[edge.from] + edge.cost;
                }
            }
            if (!isUpdated) {
                break;
            }
        }
        return d;
    }

    static class Edge {
        final int from;
        final int to;
        final int cost;

        Edge(final int from, final int to, final int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
