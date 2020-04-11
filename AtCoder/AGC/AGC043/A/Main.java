package AtCoder.AtCoder.AGC.AGC043.A;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '.') {
                    table[i][j] = true;
                }
            }
        }

        final int answer = dijkstra(table, h, w);
        System.out.println(answer);
    }

    private static int dijkstra(final boolean[][] table, final int h, final int w) {
        final PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.cost));
        final int[][] d = new int[h][w];
        for (final int[] array : d) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        d[0][0] = table[0][0] ? 0 : 1;
        queue.add(new Pair(1, 0, d[0][0] + calc(0, 0, 1, 0, table)));
        queue.add(new Pair(0, 1, d[0][0] + calc(0, 0, 0, 1, table)));

        while (!queue.isEmpty()) {
            final Pair pair = queue.poll();
            if (d[pair.i][pair.j] <= pair.cost) {
                continue;
            }

            d[pair.i][pair.j] = pair.cost;
            if (pair.i < h - 1) {
                queue.add(new Pair(pair.i + 1, pair.j, pair.cost + calc(pair.i, pair.j, pair.i + 1, pair.j, table)));
            }
            if (pair.j < w - 1) {
                queue.add(new Pair(pair.i, pair.j + 1, pair.cost + calc(pair.i, pair.j, pair.i, pair.j + 1, table)));
            }
        }

        return d[h - 1][w - 1];
    }

    private static int calc(final int i, int j, final int nextI, final int nextJ, final boolean[][] table) {
        if (table[nextI][nextJ] || !table[i][j] && !table[nextI][nextJ]) {
            return 0;
        } else {
            return 1;
        }
    }

    static class Pair {
        final int i;
        final int j;
        final int cost;

        Pair(final int i, final int j, final int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
}
