package AGC036;

import java.util.Scanner;

public class A {
    private static long MAX = 1000000000;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long s = scanner.nextLong();
        if (s < MAX) {
            System.out.println("0 0  " + s + " 0 0 1");
            return;
        }

        final long x3 = intSqrt(s) + 1;

        final long diff = x3 * x3 - s;
        long x1 = 0;
        long y2 = 0;
        for (int i = intSqrt(diff); i >= 1; i--) {
            if (diff % i == 0) {
                x1 = x3 - diff / i;
                y2 = x3 - i;
            }
        }

        if (x1 < 0 && y2 < 0) {
            System.out.println("0 " + (-1) * y2 + " " + (-1) * x1 + " 0 " + (x3 - x1) + " " + (x3 - y2));
            return;
        } else if (x1 < 0) {
            System.out.println("0 0 " + (-1) * x1 + " " + y2 + " " + (x3 - x1) + " " + x3);
            return;
        } else if (y2 < 0) {
            System.out.println("0 " + (-1) * y2 + " 0 0 " + x3 + " " + (x3 - y2));
            return;
        }

        System.out.println(x1 + " 0 0 " + y2 + " " + x3 + " " + x3);
    }

    private static int intSqrt(long a) {
        return (int) Math.sqrt(a);
    }
}
