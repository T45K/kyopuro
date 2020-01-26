package ABC078.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        scanner.nextInt();
        final int w = scanner.nextInt();

        if (n == 1) {
            System.out.println(Math.abs(w - scanner.nextInt()));
            return;
        }

        for (int i = 0; i < n - 2; i++) {
            scanner.nextInt();
        }

        final int preLast = scanner.nextInt();
        final int last = scanner.nextInt();
        System.out.println(Math.max(Math.abs(w - last), Math.abs(last - preLast)));
    }
}
