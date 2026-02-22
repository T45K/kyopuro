package ABC.ABC138.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        scanner.nextLine();
        final String s = scanner.nextLine();
        if (a >= 3200) {
            System.out.println(s);
        } else {
            System.out.println("red");
        }
    }
}
