package ABC86.D;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        final int[][] table = new int[2 * k][2 * k];

        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt() % (2 * k);
            final int y = scanner.nextInt() % (2 * k);
            final String color = scanner.next();
            if ("B".equals(color)) {
                x = (x + k) % (2 * k);
            }

            table[x][y]++;
        }

        for (int i = 1; i < 2 * k; i++) {
            for (int j = 0; j < 2 * k; j++) {
                table[i][j] += table[i - 1][j];
            }
        }

        for (int i = 0; i < 2 * k; i++) {
            for (int j = 1; j < 2 * k; j++) {
                table[i][j] += table[i][j - 1];
            }
        }

        int max = 0;
        // 基準となるマスの左下の位置がどこにあるかで分岐
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int sum;
                if (i == 0 & j == 0) {
                    sum = table[k - 1][k - 1]
                            + table[2 * k - 1][2 * k - 1] - (table[2 * k - 1][k - 1] + table[k - 1][2 * k - 1] - table[k - 1][k - 1]);
                } else if (i == 0) {
                    sum = table[k - 1][j + k - 1] - table[k - 1][j - 1]
                            + table[2 * k - 1][j - 1] - table[k - 1][j - 1]
                            + table[2 * k - 1][2 * k - 1] - (table[2 * k - 1][j + k - 1] + table[k - 1][2 * k - 1] - table[k - 1][j + k - 1]);
                } else if (j == 0) {
                    sum = table[i + k - 1][k - 1] - table[i - 1][k - 1]
                            + table[i - 1][2 * k - 1] - table[i - 1][k - 1]
                            + table[2 * k - 1][2 * k - 1] - (table[i + k - 1][2 * k - 1] + table[2 * k - 1][k - 1] - table[i + k - 1][k - 1]);
                } else {
                    sum = table[i + k - 1][j + k - 1] - (table[i + k - 1][j - 1] + table[i - 1][j + k - 1] - table[i - 1][j - 1]) // center
                            + table[2 * k - 1][j - 1] - table[i + k - 1][j - 1] //left top
                            + table[i - 1][j - 1] // left bottom
                            + table[2 * k - 1][2 * k - 1] - (table[2 * k - 1][j + k - 1] + table[i + k - 1][2 * k - 1] - table[i + k - 1][j + k - 1]) // right top
                            + table[i - 1][2 * k - 1] - table[i - 1][j + k - 1]; // right down
                }
                max = Math.max(max, Math.max(sum, n - sum));
            }
        }

        System.out.println(max);
    }

}
