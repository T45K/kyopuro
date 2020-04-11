package AtCoder.ARC.ARC005.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        int startI = -1;
        int startJ = -1;
        int goalI = -1;
        int goalJ = -1;
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                final char c = s.charAt(j);
                if (c == '#') {
                    continue;
                }

                if (c == 's') {
                    startI = i;
                    startJ = j;
                } else if (c == 'g') {
                    goalI = i;
                    goalJ = j;
                }
                table[i][j] = true;
            }
        }

        final int[][] distances = new int[h][w];
        for (final int[] array : distances) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        distances[startI][startJ] = 0;

        final PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparingInt(route -> route.cost));
        queue.addAll(getNextRoute(new Route(startI, startJ, 0), h, w, table, distances));
        while (!queue.isEmpty()) {
            final Route route = queue.poll();
            if (route.cost < distances[route.i][route.j]) {
                distances[route.i][route.j] = route.cost;
                queue.addAll(getNextRoute(route, h, w, table, distances));
            }
        }

        if (distances[goalI][goalJ] <= 2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static List<Route> getNextRoute(final Route route, final int h, final int w, final boolean[][] table, final int[][] distances) {
        final List<Route> next = new ArrayList<>();
        if (route.i > 0 && distances[route.i - 1][route.j] == Integer.MAX_VALUE) {
            if (table[route.i - 1][route.j]) {
                next.add(new Route(route.i - 1, route.j, route.cost));
            } else {
                next.add(new Route(route.i - 1, route.j, route.cost + 1));
            }
        }

        if (route.i < h - 1 && distances[route.i + 1][route.j] == Integer.MAX_VALUE) {
            if (table[route.i + 1][route.j]) {
                next.add(new Route(route.i + 1, route.j, route.cost));
            } else {
                next.add(new Route(route.i + 1, route.j, route.cost + 1));
            }
        }

        if (route.j > 0 && distances[route.i][route.j - 1] == Integer.MAX_VALUE) {
            if (table[route.i][route.j - 1]) {
                next.add(new Route(route.i, route.j - 1, route.cost));
            } else {
                next.add(new Route(route.i, route.j - 1, route.cost + 1));
            }
        }

        if (route.j < w - 1 && distances[route.i][route.j + 1] == Integer.MAX_VALUE) {
            if (table[route.i][route.j + 1]) {
                next.add(new Route(route.i, route.j + 1, route.cost));
            } else {
                next.add(new Route(route.i, route.j + 1, route.cost + 1));
            }
        }

        return next;
    }

    private static class Route {
        final int i;
        final int j;
        final int cost;

        Route(final int i, final int j, final int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
}
