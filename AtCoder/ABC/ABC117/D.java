package AtCoder.ABC.ABC117;

import java.util.Scanner;

public class D {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

        final int[] ones = new int[40];
        final int[] zeros = new int[40];

        for (int i = 0; i < n; i++) {
            final long ai = scanner.nextLong();
            final char[] aiBinary = Long.toBinaryString(ai).toCharArray();

            for (int j = 0; j < aiBinary.length; j++) {
                if (aiBinary[aiBinary.length - j - 1] == '0') {
                    zeros[j]++;
                } else {
                    ones[j]++;
                }
            }

            for (int j = aiBinary.length; j < 40; j++) {
                zeros[j]++;
            }
        }

        final char[] kBinary = Long.toBinaryString(k).toCharArray();
        long answer = 0;

        boolean free = false;
        for (int i = kBinary.length - 1; i >= 0; i--) {
            if (free) {
                final int max = Math.max(zeros[i], ones[i]);
                answer += (long)max * longPow(i);
                continue;
            }

            if (kBinary[kBinary.length - i - 1] == '0') {
                answer += (long)ones[i] * longPow(i);
                continue;
            }

            if (zeros[i] > ones[i]) {
                answer += (long)zeros[i]  * longPow(i);
                continue;
            }

            answer += (long)ones[i] * longPow(i);
            free = true;
        }

        for (int i = kBinary.length;i<40;i++){
            answer += (long)ones[i] * longPow(i);
        }

        System.out.println(answer);
    }

    private static long longPow(final int a) {
        return (long) Math.pow(2, a);
    }

    private static char[] reverseCharArray(final char[] charArray) {
        final int length = charArray.length;
        final char[] reversedCharArray = new char[length];
        for (int i = 0; i < length; i++) {
            reversedCharArray[i] = charArray[length - i - 1];
        }

        return reversedCharArray;
    }
}
