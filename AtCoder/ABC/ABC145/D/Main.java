package AtCoder.ABC.ABC145.D;

import java.util.Scanner;

public class Main {
    private static int MOD = 1000000007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();

        final int a = Math.max(x, y);
        final int b = Math.min(x, y);

        int right = a / 2;
        int under = a % 2;

        boolean hit = false;
        while (right >= 0) {
            if (right * 2 + under == a && right + under * 2 == b) {
                hit = true;
                break;
            }

            right--;
            under += 2;
        }

        if (!hit) {
            System.out.println(0);
            return;
        }

        System.out.println(new CombinationCalculator(1000000, MOD).init().calc(right + under, Math.min(right, under)));
    }

    private static long originalPow(final long a, final int b) {
        long result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
            result %= MOD;
        }

        return result;
    }

    private static class CombinationCalculator {
        private final int size;
        private final int mod;
        private final long[] fac;
        private final long[] inv;
        private final long[] finv;

        CombinationCalculator(final int size, final int mod) {
            this.size = size;
            this.mod = mod;
            this.fac = new long[size];
            this.inv = new long[size];
            this.finv = new long[size];
        }

        CombinationCalculator init() {
            fac[0] = 1;
            fac[1] = 1;
            finv[0] = 1;
            finv[1] = 1;
            inv[1] = 1;
            for (int i = 2; i < size; i++) {
                fac[i] = fac[i - 1] * i % mod;
                inv[i] = mod - inv[mod % i] * (mod / i) % mod;
                finv[i] = finv[i - 1] * inv[i] % mod;
            }
            return this;
        }

        long calc(final int n, final int k) {
            return fac[n] * (finv[k] * finv[n - k] % mod) % mod;
        }
    }
}
