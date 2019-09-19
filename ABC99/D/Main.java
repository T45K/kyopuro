package ABC99.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int c = scanner.nextInt();

        final int[][] colorTable = new int[c][c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                colorTable[i][j] = scanner.nextInt();
            }
        }

        final int[][] modTable = new int[3][c];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int tmp = scanner.nextInt() - 1;
                modTable[(i + j) % 3][tmp]++;
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < c; i++) {
            int tmp = 0;
            for (int i2 = 0; i2 < c; i2++) {
                tmp += modTable[0][i2] * colorTable[i2][i];
            }

            for (int j = 0; j < c; j++) {
                if (i % 3 == j % 3) {
                    continue;
                }

                for (int j2 = 0; j2 < c; j2++) {
                    tmp += modTable[1][j2] * colorTable[j2][j];
                }

                for (int k = 0; k < c; k++) {
                    if (i % 3 == k % 3 || j % 3 == k % 3) {
                        continue;
                    }

                    for (int k2 = 0; k2 < c; k2++) {
                        tmp += modTable[2][k2] * colorTable[k2][k];
                    }

                    answer = Math.min(answer, tmp);
                }
            }
        }

        System.out.println(answer);
    }
}
