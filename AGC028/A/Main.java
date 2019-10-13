package AGC028.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextLong();
        final long m = scanner.nextLong();
        scanner.nextLine();
        final String s = scanner.nextLine();
        final String t = scanner.nextLine();

        final long gcd = getGCD(n, m);

        for (int i = 0; i < gcd; i++) {
            final char a = s.charAt((int)(i * n / gcd));
            final char b = t.charAt((int)(i * m / gcd));

            if (a != b) {
                System.out.println(-1);
                return;
            }
        }

        final long lcm = n * m / gcd;
        System.out.println(lcm);
    }

    private static long getGCD(final long a, final long b) {
        if (b > a) {
            return getGCD(b, a);
        }

        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }
}
