package ABC.ABC075.D;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final long[] verticals = new long[n];
        final long[] horizontals = new long[n];
        final Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            final int x = scanner.nextInt();
            verticals[i] = x;
            final int y = scanner.nextInt();
            horizontals[i] = y;
            points[i] = new Point(x, y);
        }

        Arrays.sort(verticals);
        Arrays.sort(horizontals);
        long answer = Long.MAX_VALUE;
        for (int top = n - 1; top > 0; top--) {
            for (int bottom = 0; bottom < top; bottom++) {
                for (int right = n - 1; right > 0; right--) {
                    for (int left = 0; left < right; left++) {
                        int counter = 0;
                        for (final Point point : points) {
                            if (point.x >= verticals[left] && point.x <= verticals[right] && point.y >= horizontals[bottom] && point.y <= horizontals[top]) {
                                counter++;
                            }
                        }
                        if (counter >= k) {
                            answer = Math.min(answer, (verticals[right] - verticals[left]) * (horizontals[top] - horizontals[bottom]));
                        }
                    }
                }
            }
        }
        System.out.println(answer);
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
