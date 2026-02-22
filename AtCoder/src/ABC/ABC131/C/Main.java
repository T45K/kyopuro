package ABC.ABC131.C;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();
        final long c = scanner.nextLong();
        final long d = scanner.nextLong();
        final long lcd = getLCD(c, d);

        if (a == b) {
            System.out.println(0);
            return;
        }

        final long divideAC = (a - 1) / c;
        final long divideBC = b / c;
        final long divideAD = (a - 1) / d;
        final long divideBD = b / d;
        final long divideACD = (a - 1) / lcd;
        final long divideBCD = b / lcd;

        final long dDivivable = divideBD - divideAD;
        final long cDivivable = divideBC - divideAC;
        final long cdDivaivable = divideBCD - divideACD;

        System.out.println(b - a + 1 - (dDivivable + cDivivable - cdDivaivable));
    }

    private static long getLCD(final long a, final long b) {
        return a * b / gcd(a, b);
    }

    private static long gcd(final long m, final long n) {
        if (m < n) return gcd(n, m);
        if (n == 0) return m;
        return gcd(n, m % n);
    }
}
