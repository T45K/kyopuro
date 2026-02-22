package ABC.ABC029.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextInt();
        final int length = Long.toString(n).length();

        long base = 10;
        long counter = 0;
        for (int i = 0; i < length; i++) {
            counter += n / base * (base / 10);
            if (n % base >= base / 10) {
                counter += Math.min(n % base + 1 - base / 10, base / 10);
            }
            base *= 10;
        }

        System.out.println(counter);
    }
}
