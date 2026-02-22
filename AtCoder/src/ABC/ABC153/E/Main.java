package ABC.ABC153.E;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int n = scanner.nextInt();

        final int[][] dpTable = new int[n][h + 1];
        final int a1 = scanner.nextInt();
        final int b1 = scanner.nextInt();
        dpTable[0][Math.min(a1, h)] = b1;
        for (int j = 0; j < h; j++) {
            if (dpTable[0][j] != 0) {
                dpTable[0][Math.min(j + a1, h)] = dpTable[0][j] + b1;
            }
        }

        for (int i = 1; i < n; i++) {
            final int attack = scanner.nextInt();
            final int magic = scanner.nextInt();
            for (int j = 0; j <= h; j++) {
                dpTable[i][j] = dpTable[i - 1][j];
            }

            int tmp = Math.min(attack, h);
            if (dpTable[i][tmp] == 0) {
                dpTable[i][tmp] = magic;
            } else {
                dpTable[i][tmp] = Math.min(dpTable[i][tmp], magic);
            }
            for (int j = 1; j <= h; j++) {
                if (dpTable[i][j] != 0) {
                    final int y = Math.min(j + attack, h);
                    if (dpTable[i][y] == 0) {
                        dpTable[i][y] = dpTable[i][j] + magic;
                    } else {
                        dpTable[i][y] = Math.min(dpTable[i][y], dpTable[i][j] + magic);
                    }
                }
            }
        }

        System.out.println(dpTable[n - 1][h]);
    }
}
