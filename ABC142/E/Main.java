package ABC142.E;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] bitConverter = new int[n + 1];
        int pow = 1;
        for (int i = 1; i < n + 1; i++) {
            bitConverter[i] = pow;
            pow *= 2;
        }

        final Key[] keys = new Key[m];
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            int sum = 0;

            for (int j = 0; j < b; j++) {
                final int c = scanner.nextInt();
                sum += bitConverter[c];
            }
            keys[i] = new Key(a, sum);
        }

        final int[][] dpTable = new int[m][pow];
        for (final int[] array : dpTable) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }

        dpTable[0][keys[0].boxes] = keys[0].price;
        dpTable[0][0] = 0;
        for (int i = 1; i < m; i++) {
            System.arraycopy(dpTable[i - 1], 0, dpTable[i], 0, dpTable[i].length);
            for (int j = 0; j < dpTable[i].length; j++) {
                if (dpTable[i - 1][j] != Integer.MAX_VALUE) {
                    final int idx = j | keys[i].boxes;
                    dpTable[i][idx] = Math.min(dpTable[i][idx], dpTable[i - 1][j] + keys[i].price);
                }
            }
        }

        if (dpTable[m - 1][pow - 1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dpTable[m - 1][pow - 1]);
        }
    }

    static class Key {
        int price;
        int boxes;

        Key(final int price, final int boxes) {
            this.price = price;
            this.boxes = boxes;
        }
    }
}
