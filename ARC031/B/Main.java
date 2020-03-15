package ARC031.B;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Point[][] POINTS = new Point[10][10];
    private static final int[] HORIZONTAL = {1, -1, 0, 0};
    private static final int[] VERTICAL = {0, 0, 1, -1};

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final boolean[][] table = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            final String s = scanner.next();
            for (int j = 0; j < 10; j++) {
                table[i][j] = s.charAt(j) == 'o';
                POINTS[i][j] = new Point(i, j);
            }
        }

        final List<Island> islands = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (table[i][j] && !POINTS[i][j].isContained(islands)) {
                    final Island island = new Island();
                    dfs(table, i, j, island);
                    islands.add(island);
                }
            }
        }

        if (islands.size() == 1) {
            System.out.println("YES");
            return;
        }

        if (islands.size() > 4) {
            System.out.println("NO");
            return;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (POINTS[i][j].isContained(islands)) {
                    continue;
                }

                if (isConnectable(islands, i, j)) {
                    System.out.println("YES");
                    return;
                }
            }
        }

        System.out.println("NO");
    }

    private static boolean isConnectable(final List<Island> islands, final int x, final int y) {
        return islands.stream()
                .allMatch(island -> island.contains(x - 1, y) || island.contains(x + 1, y)
                        || island.contains(x, y - 1) || island.contains(x, y + 1));
    }

    private static void dfs(final boolean[][] table, final int x, final int y, final Island island) {
        island.add(POINTS[x][y]);
        for (int i = 0; i < 4; i++) {
            if (isLand(table, x + HORIZONTAL[i], y + VERTICAL[i]) && !island.contains(x + HORIZONTAL[i], y + VERTICAL[i])) {
                dfs(table, x + HORIZONTAL[i], y + VERTICAL[i], island);
            }
        }
    }

    private static boolean isLand(final boolean[][] table, final int x, final int y) {
        if (!isInRange(x, y)) {
            return false;
        }
        return table[x][y];
    }

    private static boolean isInRange(final int x, final int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    static class Island extends HashSet<Point> {
        boolean contains(final int x, final int y) {
            if (!isInRange(x, y)) {
                return false;
            }
            return this.contains(POINTS[x][y]);
        }
    }

    static class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        boolean isContained(final List<Island> islands) {
            return islands.stream()
                    .anyMatch(island -> island.contains(this));
        }
    }
}
