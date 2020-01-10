package ABC150.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextLong() / 2;
        }

        long base = array[0];
        for (int i = 1; i < n; i++) {
            final long gcd = computeGCD(base, array[i]);
            if (base / gcd % 2 == 0 || array[i] / gcd % 2 == 0) {
                System.out.println(0);
                return;
            }
            base = base * array[i] / gcd;

            if (base > m) {
                System.out.println(0);
                return;
            }
        }

        System.out.println((m / base + 1) / 2);
    }

    private static long computeGCD(final long a, final long b) {
        if (b > a) {
            return computeGCD(b, a);
        }

        if (b == 0) {
            return a;
        }

        return computeGCD(b, a % b);
    }
}
