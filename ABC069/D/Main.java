package ABC069.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();

        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        int index = 1;
        int count = array[index];
        final int[][] table = new int[h][w];
        for (int i = 0; i < h; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < w; j++) {
                    if (count == 0) {
                        count = array[++index];
                    }
                    table[i][j] = index;
                    count--;
                }
            } else {
                for (int j = w - 1; j >= 0; j--) {
                    if (count == 0) {
                        count = array[++index];
                    }
                    table[i][j] = index;
                    count--;
                }
            }
        }

        for (final int[] col : table) {
            for (final int value : col) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
