package ARC.ARC038.B;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Boolean[][] memo = new Boolean[100][100];
    private static int h;
    private static int w;

    public static void main(final String[] args) {
        for (final Boolean[] array : memo) {
            Arrays.fill(array, null);
        }

        final Scanner scanner = new Scanner(System.in);
        h = scanner.nextInt();
        w = scanner.nextInt();

        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final char[] array = scanner.next().toCharArray();
            for (int j = 0; j < w; j++) {
                table[i][j] = array[j] == '#';
            }
        }

        System.out.println(judge(0, 0, table) ? "First" : "Second");
    }

    private static boolean judge(final int i, final int j, final boolean[][] table) {
        if (isNotEnterable(i, j, table)) {
            return true;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        if (!judge(i, j + 1, table) || !judge(i + 1, j + 1, table) || !judge(i + 1, j, table)) {
            return memo[i][j] = true;
        }

        return memo[i][j] = false;
    }

    private static boolean isNotEnterable(final int i, final int j, final boolean[][] table) {
        if (i >= h || j >= w) {
            return true;
        }

        return table[i][j];
    }
}
