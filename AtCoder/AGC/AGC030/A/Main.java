package AtCoder.AGC.AGC030.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();

        final int result = a + b >= c ? b + c : a + 2 * b + 1;
        System.out.println(result);
    }
}
