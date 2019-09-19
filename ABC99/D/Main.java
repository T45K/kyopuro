package ABC99.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int c = scanner.nextInt();

        final long[][] colorTable = new long[c][c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                colorTable[i][j] = scanner.nextLong();
            }
        }

        final long[][] modTable = new long[3][c];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int tmp = scanner.nextInt() - 1;
                modTable[(i + j) % 3][tmp]++;
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < c; k++) {
                    if (i == k || j == k) {
                        continue;
                    }

                    int tmp = 0;
                    for (int l = 0; l < c; l++) {
                        tmp += modTable[0][l] * colorTable[l][i];
                        tmp += modTable[1][l] * colorTable[l][j];
                        tmp += modTable[2][l] * colorTable[l][k];
                    }
                    answer = Math.min(answer, tmp);
                }
            }
        }

        System.out.println(answer);
    }
}
