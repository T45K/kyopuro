package ABC.ABC091.D;

import java.util.Scanner;

// TODO fix
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        long allA = 0;
        for (int i = 0; i < n; i++) {
            allA ^= scanner.nextLong();
        }

        final long[] b = new long[n];
        long maxB = 0;
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextLong();
            maxB = Math.max(maxB, b[i]);
        }

        final int maxLength = Long.toBinaryString(allA * maxB).length();
        final char[][] table = new char[n][maxLength];

        long answer = 0;
        for (int i = 0; i < n; i++) {
            answer ^= allA + (b[i]*n);
        }

        System.out.println(answer);
    }
}
