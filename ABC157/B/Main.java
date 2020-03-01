package ABC157.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[][] bingo = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bingo[j][i] = scanner.nextInt();
            }
        }

        final boolean[][] isRead = new boolean[3][3];
        final int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            final int b = scanner.nextInt();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (b == bingo[j][k]) {
                        isRead[j][k] = true;
                    }
                }
            }
        }

        if (isRead[0][0]) {
            if (isRead[0][1] && isRead[0][2]
                    || isRead[1][1] && isRead[2][2]
                    || isRead[1][0] && isRead[2][0]) {
                System.out.println("Yes");
                return;
            }
        }

        if (isRead[0][1]) {
            if (isRead[1][1] && isRead[2][1]) {
                System.out.println("Yes");
                return;
            }
        }

        if (isRead[0][2]) {
            if (isRead[1][2] && isRead[2][2]
                    || isRead[1][1] && isRead[2][0]) {
                System.out.println("Yes");
                return;
            }
        }

        if (isRead[1][0]) {
            if (isRead[1][1] && isRead[1][2]) {
                System.out.println("Yes");
                return;
            }
        }

        if (isRead[2][0]) {
            if (isRead[2][1] && isRead[2][2]) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}
