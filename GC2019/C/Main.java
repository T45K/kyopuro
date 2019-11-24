package GC2019.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int d = scanner.nextInt();
        final long[] a = new long[d];
        for (int i = 0; i < d; i++) {
            a[i] = scanner.nextInt();
        }

        final long[] b = new long[d];
        for (int i = 0; i < d; i++) {
            b[i] = scanner.nextInt();
        }

        long accum = 0;
        boolean buyableFlag = false;
        long price = -1;
        for (int i = 0; i < d; i++) {
            if (buyableFlag) {
                price = Math.min(price, b[i]);
                continue;
            }

            accum += a[i];
            if (accum >= b[i]) {
                price = b[i];
                buyableFlag = true;
            }
        }

        System.out.println(price);
    }
}
