package ABC.ABC156.D;

import java.util.Scanner;

public class Main {
    private static final long MOD = (long) 1e9 + 7;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextInt();
        final long a = scanner.nextInt();
        final long b = scanner.nextInt();

        long all = 1;
        for (int i = 10; i <= n; i += 10) {
            all *= 1024;
            all %= MOD;
        }
        for (int i = 1; i <= n % 10; i++) {
            all *= 2;
            all %= MOD;
        }

        all--;

        System.out.println((all + MOD - calc(n, a) + MOD - calc(n, b)) % MOD);
    }

    private static long calc(final long n, final long k) {
        long tmp = 1;
        for (long i = 1; i <= k; i++) {
            tmp *= ((n - i + 1) * modInv(i, MOD)) % MOD;
            tmp %= MOD;
        }
        return tmp;
    }

    private static long modPow(long a, long n, final long mod) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }

    private static long modInv(final long a, final long mod) {
        return modPow(a, mod - 2, mod);
    }
}
