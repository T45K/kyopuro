package AGC.AGC020.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        while (true) {
            if (a + 1 == b) {
                System.out.println("Borys");
                return;
            }

            a++;

            if (b - 1 == a) {
                System.out.println("Alice");
                return;
            }

            b--;
        }
    }
}
