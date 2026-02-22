package ABC.ABC129;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int p = scanner.nextInt();
        final int q = scanner.nextInt();
        final int r = scanner.nextInt();

        final int max = Math.max(p, Math.max(q, r));

        System.out.println(p + q + r - max);
    }
}
