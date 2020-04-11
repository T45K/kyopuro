package AtCoder.AGC.AGC024.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final long k = scanner.nextLong();

        System.out.println(k % 2 == 0 ? a - b : b - a);
    }
}
