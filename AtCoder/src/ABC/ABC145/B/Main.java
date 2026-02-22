package ABC.ABC145.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        if (n % 2 == 1) {
            System.out.println("No");
            return;
        }

        final String substring = s.substring(0, n / 2);
        for (int i = n / 2; i < n; i++) {
            if (s.charAt(i) != substring.charAt(i - n / 2)) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }
}
