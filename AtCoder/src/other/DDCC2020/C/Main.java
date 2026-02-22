package other.DDCC2020.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int k = scanner.nextInt();

        final int[][] table = new int[h][w];
        int count = 1;
        for (int i = 0; i < h; i++) {
            final char[] array = scanner.next().toCharArray();
            for (int j = 0; j < w; j++) {
                if (array[j] == '#') {
                    table[i][j] = count++;
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 1; j < w; j++) {
                if (table[i][j] == 0 && table[i][j - 1] != 0) {
                    table[i][j] = table[i][j - 1];
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = w - 2; j >= 0; j--) {
                if (table[i][j] == 0 && table[i][j + 1] != 0) {
                    table[i][j] = table[i][j + 1];
                }
            }
        }

        for (int i = 1; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (table[i][j] == 0 && table[i - 1][j] != 0) {
                    table[i][j] = table[i - 1][j];
                }
            }
        }

        for (int i = h - 2; i >= 0; i--) {
            for (int j = 0; j < w; j++) {
                if (table[i][j] == 0 && table[i + 1][j] != 0) {
                    table[i][j] = table[i + 1][j];
                }
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
