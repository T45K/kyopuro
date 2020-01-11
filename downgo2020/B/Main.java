package downgo2020.B;

import java.util.Scanner;

public class Main {
    // TODO resolve
    private static final long MOD = 1000000007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final long f = factorization(n - 1);
        long count = 0;
        for (int i = 1; i < n - 1; i++) {
            long tmp = f;
            for (int j = i + 1; j < n; j++) {
                final long half = tmp / 2;
                count += (j - i) * half;
                count %= MOD;
                tmp -= half;
            }
            count += tmp * (n - i);
        }

        count += f;
        count %= MOD;

        System.out.println(count);
    }

    private static long factorization(final int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
            f %= MOD;
        }

        return f;
    }
}
