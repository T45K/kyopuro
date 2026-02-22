package ABC.ABC090.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextInt();
        final long m = scanner.nextInt();

        if (n == 1) {
            if (m == 1) {
                System.out.println(1);
                return;
            }

            System.out.println(m - 2);
            return;
        }

        if (m == 1) {
            System.out.println(n - 2);
            return;
        }

        System.out.println(n * m - (n * 2 + m * 2 - 4));
    }
}
