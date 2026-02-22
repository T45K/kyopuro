package ABC.ABC147.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a1 = scanner.nextInt();
        final int a2 = scanner.nextInt();
        final int a3 = scanner.nextInt();
        System.out.println(a1 + a2 + a3 >= 22 ? "bust" : "win");
    }
}
