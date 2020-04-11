package AtCoder.ABC.ABC152.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] array = new int[100];
        for (int i = 1; i <= n; i++) {
            if (i % 10 == 0) {
                continue;
            }

            final String s = Integer.toString(i);
            array[(s.charAt(0) - '0') * 10 + s.charAt(s.length() - 1) - '0']++;
        }

        int count = 0;
        for (int i = 11; i <= 99; i++) {
            final int reverseNum = i % 10 * 10 + i / 10;
            count += array[i] * array[reverseNum];
        }

        System.out.println(count);
    }
}
