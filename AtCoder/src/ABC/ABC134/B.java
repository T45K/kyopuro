package ABC.ABC134;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int d = scanner.nextInt();

        System.out.println((n + 2 * d) / (2 * d + 1));
    }
}
