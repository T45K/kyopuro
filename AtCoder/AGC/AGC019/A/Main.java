package AtCoder.AGC.AGC019.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long q = scanner.nextLong();
        final long h = Math.min(scanner.nextLong(), 2 * q);
        final long s = Math.min(scanner.nextLong(), 2 * h);
        final long d = Math.min(scanner.nextLong(), 2 * s);
        final long n = scanner.nextInt();

        if (n % 2 == 0) {
            System.out.println(n / 2 * d);
        } else {
            System.out.println(n / 2 * d + s);
        }
    }
}
