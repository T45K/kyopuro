package ABC018.C;

import java.util.Scanner;

/*
位置を求める系 ちょっと考えた 結局上下左右から累積和的なことをするだけ
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int r = scanner.nextInt();
        final int c = scanner.nextInt();
        final int k = scanner.nextInt();

        final int[][] table = new int[r][c];
        for (int i = 0; i < r; i++) {
            final String s = scanner.next();
            for (int j = 0; j < c; j++) {
                table[i][j] = s.charAt(j) == 'o' ? 0 : k;
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (table[i][j] < table[i - 1][j]) {
                    table[i][j] = table[i - 1][j] - 1;
                }
            }
        }

        for (int i = r - 2; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                if (table[i][j] < table[i + 1][j]) {
                    table[i][j] = table[i + 1][j] - 1;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (table[i][j] < table[i][j - 1]) {
                    table[i][j] = table[i][j - 1] - 1;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = c - 2; j >= 0; j--) {
                if (table[i][j] < table[i][j + 1]) {
                    table[i][j] = table[i][j + 1] - 1;
                }
            }
        }

        int count = 0;
        for (int i = k - 1; i < r - k + 1; i++) {
            for (int j = k - 1; j < c - k + 1; j++) {
                if (table[i][j] == 0) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
