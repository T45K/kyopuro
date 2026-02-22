package ABC.ABC070.C;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        long lcm = scanner.nextLong();
        for (int i = 0; i < n - 1; i++) {
            final long tmp = scanner.nextLong();
            final long gcd = computeGCD(lcm, tmp);
            lcm = BigInteger.valueOf(lcm).multiply(BigInteger.valueOf(tmp)).divide(BigInteger.valueOf(gcd)).longValue();
        }

        System.out.println(lcm);
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
