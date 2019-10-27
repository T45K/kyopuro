package ABC144.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        if (a >= 10) {
            System.out.println(-1);
            return;
        }

        final int b = scanner.nextInt();
        if (b >= 10) {
            System.out.println(-1);
            return;
        }

        System.out.println(a * b);
    }
}
