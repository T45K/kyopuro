package ABC005.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[][] table = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                table[i][j] = scanner.nextInt() + table[i][j - 1];
            }
        }


        final int[] array = new int[n * n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = j; k <= n; k++) {
                    int sum = 0;
                    for (int l = i; l <= n; l++) {
                        sum += table[l][k] - table[l][j - 1];
                        final int square = (l - i + 1) * (k - j + 1);
                        array[square] = Math.max(sum, array[square]);
                    }
                }
            }
        }

        for (int i = 1; i < array.length; i++) {
            array[i] = Math.max(array[i], array[i - 1]);
        }

        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            final int p = scanner.nextInt();
            System.out.println(array[p]);
        }
    }
}
