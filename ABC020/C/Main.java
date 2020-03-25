package ABC020.C;

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
        final int t = scanner.nextInt();

        int startI = -1;
        int startJ = -1;
        int goalI = -1;
        int goalJ = -1;

        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                if (s.charAt(j) == '#') {
                    continue;
                }

                if (s.charAt(j) == 'S') {
                    startI = i;
                    startJ = j;
                } else if (s.charAt(j) == 'G') {
                    goalI = i;
                    goalJ = j;
                }
                table[i][j] = true;
            }
        }

        final int answer = binarySearch(1, t + 1, table, startI, startJ, goalI, goalJ, h, w, t);
        System.out.println(answer);
    }

    private static int binarySearch(final int begin, final int end, final boolean[][] table, final int startI, final int startJ, final int goalI, final int goalJ, final int h, final int w, final int t) {
        if (end - begin <= 1) {
            return begin;
        }

        final int mid = (begin + end) / 2;
        if (isOk(table, mid, startI, startJ, goalI, goalJ, h, w, t)) {
            return binarySearch(mid, end, table, startI, startJ, goalI, goalJ, h, w, t);
        } else {
            return binarySearch(begin, mid, table, startI, startJ, goalI, goalJ, h, w, t);
        }
    }

    private static boolean isOk(final boolean[][] table, final int x, final int startI, final int startJ, final int goalI, final int goalJ, final int h, final int w, final long t) {
        final long[][] distances = new long[h][w];
        for (final long[] array : distances) {
            Arrays.fill(array, Long.MAX_VALUE);
        }
        distances[startI][startJ] = 0;

        final PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparingLong(r -> r.cost));
        queue.addAll(next(new Route(startI, startJ, 0), x, h, w, table, distances));
        while (!queue.isEmpty()) {
            final Route route = queue.poll();
            if (distances[route.i][route.j] < route.cost) {
                continue;
            }
            distances[route.i][route.j] = route.cost;
            queue.addAll(next(route, x, h, w, table, distances));
        }

        return distances[goalI][goalJ] <= t;
    }

    private static List<Route> next(final Route route, final long x, final int h, final int w, final boolean[][] table, final long[][] distances) {
        final int i = route.i;
        final int j = route.j;
        final long cost = route.cost;

        final List<Route> list = new ArrayList<>();
        if (i > 0 && distances[i - 1][j] == Long.MAX_VALUE) {
            if (table[i - 1][j]) {
                list.add(new Route(i - 1, j, cost + 1));
            } else {
                list.add(new Route(i - 1, j, cost + x));
            }
        }

        if (i < h - 1 && distances[i + 1][j] == Long.MAX_VALUE) {
            if (table[i + 1][j]) {
                list.add(new Route(i + 1, j, cost + 1));
            } else {
                list.add(new Route(i + 1, j, cost + x));
            }
        }

        if (j > 0 && distances[i][j - 1] == Long.MAX_VALUE) {
            if (table[i][j - 1]) {
                list.add(new Route(i, j - 1, cost + 1));
            } else {
                list.add(new Route(i, j - 1, cost + x));
            }
        }

        if (j < w - 1 && distances[i][j + 1] == Long.MAX_VALUE) {
            if (table[i][j + 1]) {
                list.add(new Route(i, j + 1, cost + 1));
            } else {
                list.add(new Route(i, j + 1, cost + x));
            }
        }

        return list;
    }

    private static class Route {
        final int i;
        final int j;
        final long cost;

        Route(final int i, final int j, final long cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
}
