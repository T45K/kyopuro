package ABC.ABC091.C;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Point> reds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reds.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }

        final List<Point> blues = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            blues.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }

        reds.sort(Comparator.comparingInt(point -> point.x));
        blues.sort(Comparator.comparingInt(point -> point.y));
        Collections.reverse(reds);

        int counter = 0;
        for (int i = 0; i < n; i++) {
            final Point red = reds.get(i);
            for (int j = 0; j < blues.size(); j++) {
                final Point blue = blues.get(j);
                if (red.x < blue.x && red.y < blue.y) {
                    counter++;
                    blues.remove(j);
                    break;
                }
            }
        }

        System.out.println(counter);
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
