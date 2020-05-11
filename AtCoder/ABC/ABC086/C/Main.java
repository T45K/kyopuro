package AtCoder.ABC.ABC086.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        Point currentPoint = new Point(0, 0);

        for (int i = 0; i < n; i++) {
            final int time = scanner.nextInt();
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();

            final int distance = Math.abs(x - currentPoint.x) + Math.abs(y - currentPoint.y);
            if (distance > time || (time - distance) % 2 == 1) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    static class Point {
        int x;
        int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
