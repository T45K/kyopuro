package nikkei2019.B;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    private static BigInteger MOD = BigInteger.valueOf(998244353);

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            final int tmp = scanner.nextInt();
            if (i == 0 && tmp != 0) {
                System.out.println(0);
                return;
            } else if (i != 0 && tmp == 0) {
                System.out.println(0);
                return;
            }

            array[tmp]++;
        }

        int last = n - 1;
        for (; last >= 0; last--) {
            if (array[last] != 0) {
                break;
            }
        }

        BigInteger answer = BigInteger.ONE;
        long pre = 1;
        for (int i = 1; i <= last; i++) {
            if (array[i] == 0) {
                System.out.println(0);
                return;
            }
            answer = answer.multiply(originalPow(pre, array[i])).mod(MOD);
            pre = array[i];
        }

        System.out.println(answer);
    }

    private static BigInteger originalPow(final long a, final long b) {
        BigInteger result = BigInteger.ONE;
        for (long i = 0; i < b; i++) {
            result = result.multiply(BigInteger.valueOf(a)).mod(MOD);
        }

        return result;
    }
}
