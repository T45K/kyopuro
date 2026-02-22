package ABC.ABC088.D;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] table = new boolean[h][w];

        int black = 0;
        for (int i = 0; i < h; i++) {
            final char[] col = scanner.next().toCharArray();
            for (int j = 0; j < w; j++) {
                if (col[j] == '.') {
                    table[i][j] = true;
                } else {
                    black++;
                }
            }
        }

        final Deque<Pair> points = new ArrayDeque<>();
        points.add(new Pair(0, 0, 1));

        while (!points.isEmpty()) {
            final Pair pair = points.poll();
            if (pair.x == w - 1 && pair.y == h - 1) {
                System.out.println(h * w - (black + pair.depth));
                return;
            }

            if (pair.x > 0 && table[pair.y][pair.x - 1]) {
                table[pair.y][pair.x - 1] = false;
                points.add(new Pair(pair.x - 1, pair.y, pair.depth + 1));
            }

            if (pair.x < w - 1 && table[pair.y][pair.x + 1]) {
                table[pair.y][pair.x + 1] = false;
                points.add(new Pair(pair.x + 1, pair.y, pair.depth + 1));
            }

            if (pair.y < h - 1 && table[pair.y + 1][pair.x]) {
                table[pair.y + 1][pair.x] = false;
                points.add(new Pair(pair.x, pair.y + 1, pair.depth + 1));
            }

            if (pair.y > 0 && table[pair.y - 1][pair.x]) {
                table[pair.y - 1][pair.x] = false;
                points.add(new Pair(pair.x, pair.y - 1, pair.depth + 1));
            }
        }

        System.out.println(-1);
    }

    static class Pair {
        final int x;
        final int y;
        final int depth;

        Pair(final int x, final int y, final int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
