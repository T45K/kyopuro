package ABC.ABC147.D;

import java.util.Scanner;

public class Main {
    private static long MOD = 1000000007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = new long[61];
        for (int i = 0; i < n; i++) {
            final long a = scanner.nextLong();
            final String s = Long.toBinaryString(a);
            for (int j = s.length() - 1; j >= 0; j--) {
                if (s.charAt(j) == '1') {
                    array[s.length() - j - 1]++;
                }
            }
        }
        long answer = 0;
        for (int i = 0; i < array.length; i++) {
            final long l = originalPow(i);
            final long l2 = array[i] * (n - array[i]) % MOD;
            answer += (l * l2) % MOD;
            answer %= MOD;
        }

        System.out.println(answer);
    }

    private static long originalPow(final long a) {
        long returnValue = 1;
        for (long i = 0; i < a; i++) {
            returnValue *= 2;
            returnValue %= MOD;
        }

        return returnValue;
    }
}
