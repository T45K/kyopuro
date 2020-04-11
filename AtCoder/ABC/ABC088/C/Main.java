package AtCoder.ABC.ABC088.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[][] table = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        int a1;
        int a2;
        int a3;
        int b1;
        int b2;
        int b3;

        for (a1 = 0; a1 <= table[0][0]; a1++) {
            b1 = table[0][0] - a1;
            a2 = table[1][0] - b1;
            if (a2 < 0) continue;
            a3 = table[2][0] - b1;
            if (a3 < 0) continue;
            b2 = table[0][1] - a1;
            if (b2 < 0) continue;
            b3 = table[0][2] - a1;
            if (b3 < 0) continue;
            if (a2 + b2 != table[1][1]) continue;
            if (a2 + b3 != table[1][2]) continue;
            if (a3 + b2 != table[2][1]) continue;
            if (a3 + b3 != table[2][2]) continue;
            System.out.println("Yes");
            return;
        }

        System.out.println("No");
    }
}
