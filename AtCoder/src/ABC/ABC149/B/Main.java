package ABC.ABC149.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();
        final long k = scanner.nextLong();

        if (k < a) {
            System.out.println((a - k) + " " + b);
        } else {
            final long diff = k - a;
            System.out.println(0 + " " + Math.max(0, (b - diff)));
        }
    }
}
