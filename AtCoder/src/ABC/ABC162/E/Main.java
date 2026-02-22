package ABC.ABC162.E;

import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        long sum = 0;
        final long[] array = new long[k + 1];
        for (int i = k; i >= 1; i--) {
            final int nums = k / i;
            long multi = iterativePow(nums, n);

            for (int j = 2; i * j <= k; j++) {
                multi = (multi + MOD - array[i * j]) % MOD;
            }

            sum += (multi * i) % MOD;
            sum %= MOD;
            array[i] = multi;
        }
        System.out.println(sum);
    }

    private static long iterativePow(long a, long b) {
        if (a == 1) {
            return 1;
        }
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
            }
            a *= a;
            a %= MOD;
            b >>= 1;
            tmp %= MOD;
        }

        return tmp;
    }
}
