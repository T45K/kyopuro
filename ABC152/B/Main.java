package ABC152.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        for (int i = 0; i < Math.max(a, b); i++) {
            System.out.print(Math.min(a,b));
        }
        System.out.println();
    }
}
