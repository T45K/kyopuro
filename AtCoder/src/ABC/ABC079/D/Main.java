package ABC.ABC079.D;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final int[][] table = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final int[] magicPowers = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i == 1) {
                continue;
            }

            final int min = table[i][1];
            magicPowers[i] = search(table, 0, i, min);
        }

        int answer = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                final int tmp = scanner.nextInt();
                if (tmp != -1) {
                    answer += magicPowers[tmp];
                }
            }
        }

        System.out.println(answer);
    }

    private static int search(final int[][] table, final int magicPower, final int index, final int min) {
        if (index == 1) {
            return magicPower + table[index][1];
        }
        if (magicPower > min) {
            return min;
        }

        int tmp = min;
        for (int i = 0; i < 10; i++) {
            if (i == index) {
                continue;
            }
            tmp = Math.min(tmp, search(table, magicPower + table[index][i], i, min));
        }

        return tmp;
    }
}
