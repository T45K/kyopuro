package AtCoder.ABC.ABC096.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] table = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            final char[] line = scanner.next().toCharArray();
            int j = 0;
            for (final char c : line) {
                if (c == '#') {
                    table[i][j] = true;
                }
                j++;
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!table[i][j] || check(table, j, i)) {
                    continue;
                }

                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    private static boolean check(final boolean[][] table, final int x, final int y) {
        final int h = table.length;
        final int w = table[0].length;
        return check(h, w, table, x, y - 1)
                || check(h, w, table, x, y + 1)
                || check(h, w, table, x + 1, y)
                || check(h, w, table, x - 1, y);
    }

    private static boolean check(final int h, final int w, final boolean[][] table, final int x, final int y) {
        if (x < 0 || x >= w) {
            return false;
        }

        if (y < 0 || y >= h) {
            return false;
        }

        return table[y][x];
    }
}
