package ABC148.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();

        final long gcd = computeGCD(a, b);
        System.out.println(a * b / gcd);
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
