package AtCoder.ABC.ABC065.C;

import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextInt();
        final long m = scanner.nextInt();

        if (Math.abs(n - m) > 1) {
            System.out.println(0);
            return;
        }

        final long nFactorial = factorial(n);
        final long mFactorial = factorial(m);
        if (n == m) {
            System.out.println(nFactorial * mFactorial * 2 % MOD);
        } else {
            System.out.println(nFactorial * mFactorial % MOD);
        }
    }

    private static long factorial(final long a) {
        long retVal = 1;
        for (long i = 2; i <= a; i++) {
            retVal *= i;
            retVal %= MOD;
        }
        return retVal;
    }
}
