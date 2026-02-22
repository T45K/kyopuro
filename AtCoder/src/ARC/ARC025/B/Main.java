package ARC.ARC025.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final int[][] table = new int[h + 1][w + 1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                final int c = scanner.nextInt();
                table[i][j] = (j % 2 ^ i % 2) == 0 ? c : -c;
            }
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                table[i][j] += table[i][j - 1];
            }
        }

        int max = 0;
        for (int jFrom = 0; jFrom < w; jFrom++) {
            for (int jTo = jFrom + 1; jTo <= w; jTo++) {
                for (int iFrom = 0; iFrom < h; iFrom++) {
                    int tmp = 0;
                    for (int iTo = iFrom + 1; iTo <= h; iTo++) {
                        tmp += table[iTo][jTo] - table[iTo][jFrom];
                        if(tmp == 0){
                            max = Math.max(max,(iTo - iFrom) * (jTo - jFrom));
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }
}
