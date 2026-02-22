package ABC.ABC133;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int i = scanner.nextInt();
        final int j = scanner.nextInt();
        final int k = scanner.nextInt();

        System.out.println(Math.min(i * j, k));
    }
}
