package ABC.ABC108.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        if (k % 2 == 0) {
            long counterA = 0;
            for (int i = 1; i <= n; i++) {
                if (i % k == 0) {
                    counterA++;
                }
            }

            long counterB = 0;
            for (int i = 1; i <= n; i++) {
                if (i % k == k / 2) {
                    counterB++;
                }
            }

            System.out.println((long) Math.pow(counterA, 3L) + (long) Math.pow(counterB, 3L));
            return;
        }

        long counter = 0;
        for (int i = 1; i <= n; i++) {
            if (i % k == 0) {
                counter++;
            }
        }

        System.out.println((long) Math.pow(counter, 3L));
    }
}
