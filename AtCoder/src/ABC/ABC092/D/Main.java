package ABC.ABC092.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int white = scanner.nextInt();
        final int black = scanner.nextInt();

        final char[][] table = new char[100][100];

        int tmpWhite = white - 1;
        for (int i = 0; i < 49; i++) {
            for (int j = 0; j < 100; j++) {
                if (j != 0 && j != 99 && j % 3 == i % 3 && tmpWhite > 0) {
                    table[i][j] = '.';
                    tmpWhite--;
                } else {
                    table[i][j] = '#';
                }
            }
        }

        for (int j = 0; j < 100; j++) {
            table[49][j] = '#';
            table[50][j] = '.';
        }

        int tmpBlack = black - 1;
        for (int i = 51; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (j != 0 && j != 99 && j % 3 == i % 3 && tmpBlack > 0) {
                    table[i][j] = '#';
                    tmpBlack--;
                } else {
                    table[i][j] = '.';
                }
            }
        }

        System.out.print(100+" ");
        System.out.println(100);
        for (final char[] chars : table) {
            for (final char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
