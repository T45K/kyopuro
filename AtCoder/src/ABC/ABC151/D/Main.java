package ABC.ABC151.D;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final int[][] table = new int[h][w];
        for (int i = 0; i < h; i++) {
            final String line = scanner.next();
            for (int j = 0; j < line.toCharArray().length; j++) {
                table[i][j] = line.charAt(j) == '.' ? 0 : -1;
            }
        }

        int longestDistance = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (table[i][j] == -1) {
                    continue;
                }

                final int[][] tmp = new int[h][w];
                for (int k = 0; k < tmp.length; k++) {
                    System.arraycopy(table[k], 0, tmp[k], 0, w);
                }

                tmp[i][j] = 1;
                longestDistance = Math.max(getLongestDistance(tmp, h, w, j, i), longestDistance);
            }
        }
        System.out.println(longestDistance - 1);
    }

    private static int getLongestDistance(final int[][] table, final int h, final int w, final int initX, final int initY) {
        final Deque<Point> points = new ArrayDeque<>();
        final Point initPoint = new Point(initX, initY);
        points.add(initPoint);

        while (!points.isEmpty()) {
            final Point point = points.pollFirst();
            final int x = point.x;
            final int y = point.y;
            if (x > 0 && table[y][x - 1] == 0) {
                table[y][x - 1] = table[y][x] + 1;
                points.add(new Point(x - 1, y));
            }

            if (x < w - 1 && table[y][x + 1] == 0) {
                table[y][x + 1] = table[y][x] + 1;
                points.add(new Point(x + 1, y));
            }

            if (y > 0 && table[y - 1][x] == 0) {
                table[y - 1][x] = table[y][x] + 1;
                points.add(new Point(x, y - 1));
            }

            if (y < h - 1 && table[y + 1][x] == 0) {
                table[y + 1][x] = table[y][x] + 1;
                points.add(new Point(x, y + 1));
            }
        }

        int max = 0;
        for (final int[] array : table) {
            for (final int i : array) {
                max = Math.max(i, max);
            }
        }

        return max;
    }

    static class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
