package ABC144.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final double a = scanner.nextDouble();
        final double b = scanner.nextDouble();
        final double x = scanner.nextDouble();

        if (x >= a * a * b / 2) {
            final double y = 2 * (a * a * b - x) / (a * a);

            final double rad = Math.atan2(y, a);
            final double degree = Math.toDegrees(rad);

            System.out.println(degree);
        } else {
            final double y = 2 * x / (a * b);
            final double rad = Math.atan2(b, y);
            final double degree = Math.toDegrees(rad);

            System.out.println(degree);
        }
    }
}
