package ABC095.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();

        int result;
        if (x < y) {
            result = Math.min((a + b) * x, c * 2 * x);
            result += Math.min(b * (y - x), 2 * c * (y - x));
        } else {
            result = Math.min((a + b) * y, c * 2 * y);
            result += Math.min(a * (x - y), c * 2 * (x - y));
        }

        System.out.println(result);
    }
}
