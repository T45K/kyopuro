package ABC.ABC062.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long h = scanner.nextInt();
        final long w = scanner.nextInt();

        System.out.println(Math.min(calcMin(h, w), calcMin(w, h)));
    }

    private static long calcMin(final long a, final long b) {
        long min = Long.MAX_VALUE;
        for (long i = 1; i < a; i++) {
            final long p = i * b;

            final long p1 = (a - i) / 2 * b;
            if ((a - i) % 2 == 0) {
                min = Math.min(min, Math.abs(p - p1));
            } else {
                final long p2 = ((a - i) / 2 + 1) * b;
                min = Math.min(min, max(p, p1, p2) - min(p, p1, p2));
            }

            final long p3 = (a - i) * (b / 2);
            if (b % 2 == 0) {
                min = Math.min(min, Math.abs(p - p3));
            } else {
                final long p4 = (a - i) * (b / 2 + 1);
                min = Math.min(min, max(p, p3, p4) - min(p, p3, p4));
            }
        }
        return min;
    }

    private static long max(final long... values) {
        Arrays.sort(values);
        return values[values.length - 1];
    }

    private static long min(final long... values) {
        Arrays.sort(values);
        return values[0];
    }
}
