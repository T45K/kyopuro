package MITSUI2019.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int x = scanner.nextInt();
        final boolean[][] table = new boolean[6][x + 1];
        for (int i = 0; i < 6; i++) {
            final int price = 100 + i;
            if (x % price == 0) {
                System.out.println(1);
                return;
            }

            for (int j = 1; j * price <= x; j++) {
                table[i][j * price] = true;
            }

            if (i == 0) {
                continue;
            }

            for (int j = 1; j <= x; j++) {
                table[i][j] |= table[i - 1][j];
            }

            for (int j = price + 1; j <= x; j++) {
                table[i][j] |= table[i - 1][j - price] || table[i][j - price];
            }
        }

        System.out.println(table[5][x] ? 1 : 0);
    }
}
