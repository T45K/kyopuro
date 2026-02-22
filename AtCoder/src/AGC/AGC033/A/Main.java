package AGC.AGC033.A;

import java.util.Arrays;
import java.util.Scanner;

/*
上下左右累積和
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final int[][] table = new int[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = s.charAt(j) == '#' ? 1 : Integer.MAX_VALUE - 1;
            }
        }

        for (int i = 1; i < h; i++) {
            for (int j = 0; j < w; j++) {
                table[i][j] = Math.min(table[i][j], table[i - 1][j] + 1);
            }
        }

        for (int i = h - 2; i >= 0; i--) {
            for (int j = 0; j < w; j++) {
                table[i][j] = Math.min(table[i][j], table[i + 1][j] + 1);
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 1; j < w; j++) {
                table[i][j] = Math.min(table[i][j], table[i][j - 1] + 1);
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = w - 2; j >= 0; j--) {
                table[i][j] = Math.min(table[i][j], table[i][j + 1] + 1);
            }
        }

        final int answer = Arrays.stream(table)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt() - 1;

        System.out.println(answer);
    }
}
