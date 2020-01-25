package ABC080.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int c = scanner.nextInt();

        final boolean[][] table = new boolean[c][100001];
        for (int i = 0; i < n; i++) {
            final int s = scanner.nextInt();
            final int t = scanner.nextInt();
            final int channel = scanner.nextInt() - 1;
            for (int j = s - 1; j < t; j++) {
                table[channel][j] = true;
            }
        }

        int max = 0;
        for (int i = 0; i < 100001; i++) {
            int tmp = 0;
            for (int j = 0; j < c; j++) {
                tmp += table[j][i] ? 1 : 0;
            }
            max = Math.max(max, tmp);
        }

        System.out.println(max);
    }
}
