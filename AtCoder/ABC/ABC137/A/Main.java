package AtCoder.ABC.ABC137.A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        System.out.println(Math.max(a + b, Math.max(a - b, a * b)));
    }
}
