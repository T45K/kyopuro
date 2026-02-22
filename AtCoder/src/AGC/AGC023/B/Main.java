package AGC.AGC023.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            final String s = scanner.next();
            for (int j = 0; j < n; j++) {
                table[i][j] = s.charAt(j) - 'a';
            }
        }

        System.out.println(countGood(table, n) * n);
    }

    private static int countGood(final int[][] table, final int length) {
        int counter = 0;
        for (int c = 0; c < length; c++) {
            boolean flag = true;
            for (int i = 0; i < length && flag; i++) {
                for (int j = i + 1; j < length; j++) {
                    if (table[i][j] != table[j][i]) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                counter++;
            }

            final int[] copy = new int[length];
            System.arraycopy(table[0], 0, copy, 0, length);
            for (int i = 1; i < length; i++) {
                System.arraycopy(table[i], 0, table[i - 1], 0, length);
            }
            System.arraycopy(copy, 0, table[length - 1], 0, length);
        }
        return counter;
    }
}
