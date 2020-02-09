package ABC074.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt() * 100;
        final int b = scanner.nextInt() * 100;
        final int c = scanner.nextInt();
        final int d = scanner.nextInt();
        final int e = scanner.nextInt();
        final int f = scanner.nextInt();

        int water = a;
        int sugar = 0;
        for (int i = 100; i < f; i += 100) {
            if (!isEnable(a, b, i)) {
                continue;
            }
            for (int j = 0; j <= Math.min(e * i / 100, f - i); j++) {
                if (!isEnable(c, d, j)) {
                    continue;
                }
                if (concentration(i, j) > concentration(water, sugar)) {
                    water = i;
                    sugar = j;
                }
            }
        }
        System.out.println(water + sugar + " " + sugar);
    }

    private static boolean isEnable(final int a, final int b, final int f) {
        for (int i = 0; i <= f; i += a) {
            if ((f - i) % b == 0) {
                return true;
            }
        }
        return false;
    }

    private static double concentration(final double water, final double sugar) {
        return 100 * sugar / (water + sugar);
    }
}
