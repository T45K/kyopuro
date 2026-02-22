package ABC.ABC125;

import java.util.Scanner;

public class D {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int number = scanner.nextInt();
        final long[] longs = new long[number];

        for (int i = 0; i < longs.length; i++) {
            longs[i] = scanner.nextLong();
        }

        final long[][] dpTable = new long[4][number];

        /*
        0 直前，今回ともに操作なし
        1 直前に操作なし，今回は操作あり
        2 直前も今回も操作あり
        3 直前に操作あり，今回は操作なし
         */

        dpTable[0][0] = longs[0];
        dpTable[1][0] = -longs[0];
        dpTable[2][0] = -longs[0];
        dpTable[3][0] = longs[0];

        for (int i = 0; i < number - 2; i++) {
            dpTable[0][i + 1] = Math.max(dpTable[0][i] + longs[i + 1], dpTable[3][i] + longs[i + 1]);
            dpTable[1][i + 1] = Math.max(dpTable[0][i] - longs[i + 1], dpTable[3][i] - longs[i + 1]);
            dpTable[2][i + 1] = Math.max(dpTable[1][i] + longs[i + 1], dpTable[2][i] + longs[i + 1]);
            dpTable[3][i + 1] = Math.max(dpTable[1][i] - longs[i + 1], dpTable[2][i] - longs[i + 1]);
        }

        dpTable[0][number - 1] = dpTable[0][number - 2] + longs[number - 1];
        dpTable[1][number - 1] = dpTable[1][number - 2] - longs[number - 1];
        dpTable[2][number - 1] = dpTable[2][number - 2] - longs[number - 1];
        dpTable[3][number - 1] = dpTable[3][number - 2] + longs[number - 1];

        System.out.println(Math.max(Math.max(dpTable[0][number - 1], dpTable[1][number - 1]), Math.max(dpTable[2][number - 1], dpTable[3][number - 1])));
    }
}
