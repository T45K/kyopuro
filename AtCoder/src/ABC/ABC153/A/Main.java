package ABC.ABC153.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int a = scanner.nextInt();

        System.out.println((h + a - 1) / a);
    }
}
