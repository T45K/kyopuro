package ABC159.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        System.out.println(n * (n - 1) / 2 + m * (m - 1) / 2);
    }
}
