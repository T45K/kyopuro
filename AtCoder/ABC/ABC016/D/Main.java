package AtCoder.ABC.ABC016.D;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

/*
数学 see http://www5d.biglobe.ne.jp/~tomoya03/shtml/algorithm/Intersection.htm ベクトル面積を求めて正負を出してもよかった(x1*y2 - x2*y1) オーバーフローに注意
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long ax = scanner.nextInt();
        final long ay = scanner.nextInt();
        final long bx = scanner.nextInt();
        final long by = scanner.nextInt();

        final BiFunction<Long, Long, Long> calc1 = (x, y) -> (ax - bx) * (y - ay) + (ay - by) * (ax - x);

        final int n = scanner.nextInt();
        final List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }

        int intersectCount = 0;
        for (int i = 0; i < points.size(); i++) {
            final Point point1 = points.get(i);
            final Point point2 = points.get((i + 1) % n);
            final BiFunction<Long, Long, Long> calc2 = (x, y) -> (point1.x - point2.x) * (y - point1.y) + (point1.y - point2.y) * (point1.x - x);
            if (calc1.apply(point1.x, point1.y) * calc1.apply(point2.x, point2.y) < 0 && calc2.apply(ax, ay) * calc2.apply(bx, by) < 0) {
                intersectCount++;
            }
        }

        System.out.println(intersectCount / 2 + 1);
    }

    private static class Point {
        final long x;
        final long y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }
}
