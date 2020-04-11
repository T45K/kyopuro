package AtCoder.ABC.ABC160.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();

        final int[][] table = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                table[i][j] = Math.abs(i - j);
            }
        }

        table[x][y] = 1;
        table[y][x] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                table[i][j] = Math.min(table[i][j], table[i][x] + table[x][j]);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                table[i][j] = Math.min(table[i][j], table[i][y] + table[y][j]);
            }
        }

        final int[] counter = new int[n];
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                counter[table[i][j]]++;
            }
        }

        for (int i = 1; i < n; i++) {
            System.out.println(counter[i]);
        }
    }
}
