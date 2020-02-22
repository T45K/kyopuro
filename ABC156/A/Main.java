package ABC156.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int r = scanner.nextInt();

        System.out.println(r + 100 * (10 - Math.min(n, 10)));
    }
}
