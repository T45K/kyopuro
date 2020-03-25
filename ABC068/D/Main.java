package ABC068.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long k = scanner.nextLong();

        if (k == 0) {
            System.out.println(2);
            System.out.println("0 0");
        } else if (k == 1) {
            System.out.println(2);
            System.out.println("2 0");
        } else if (k <= 50) {
            System.out.println(k);
            for (long i = 1; i <= k; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        } else {
            System.out.println(50);
            final long div = k / 50L;
            final long mod = k % 50L;
            for (long l = 1; l <= 50; l++) {
                System.out.print(50 - l + div + (l <= mod ? 1 : 0));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
