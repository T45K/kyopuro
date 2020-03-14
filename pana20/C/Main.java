package pana20.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long a = scanner.nextInt();
        final long b = scanner.nextInt();
        final long c = scanner.nextInt();

        if (c - (a + b) <= 0) {
            System.out.println("No");
            return;
        }

        if (4 * a * b < c * c - 2 * a * c - 2 * b * c + 2 * a * b + a * a + b * b) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
