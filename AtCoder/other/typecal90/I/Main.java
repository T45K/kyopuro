package AtCoder.other.typecal90.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.function.Predicate;

// 想定解だが通らない
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Point[] array = new Point[n];
        for (int i = 0; i < n; i++) {
            final Point point = new Point(scanner.nextInt(), scanner.nextInt());
            array[i] = point;
        }
        final double max = Arrays.stream(array)
            .mapToDouble(base -> {
                final Point[] sorted = Arrays.stream(array)
                    .filter(Predicate.not(base::equals))
                    .sorted(Comparator.comparingDouble(base::calcDeg))
                    .toArray(Point[]::new);
                return Arrays.stream(sorted)
                    .mapToDouble(tmp -> {
                        if (base.equals(tmp)) {
                            return 0;
                        }
                        final double deg = base.calcDeg(tmp);
                        final double opposite = (deg + 180) % 360;
                        if (opposite < base.calcDeg(sorted[0]) || opposite > base.calcDeg(sorted[sorted.length - 1])) {
                            return calcDeclination(deg, base.calcDeg(sorted[sorted.length - 1]));
                        }
                        final int index = binarySearch(sorted, opposite, base, 0, sorted.length - 1);
                        final double a = calcDeclination(deg, base.calcDeg(sorted[index]));
                        final double b = calcDeclination(deg, base.calcDeg(sorted[index - 1]));
                        return Math.max(a, b);
                    })
                    .max()
                    .orElse(0);
            })
            .max()
            .orElse(0);
        System.out.println(max);
    }

    private static double calcDeclination(final double a, final double b) {
        final double abs = Math.abs(a - b);
        return Math.min(abs, 360 - abs);
    }

    private static int binarySearch(final Point[] sorted, final double target, final Point point, final int begin, final int end) {
        if (end - begin <= 1) {
            return end;
        }

        final int mid = (begin + end) / 2;
        final Point p = sorted[mid];
        final double deg = point.calcDeg(p);
        if (deg > target) {
            return binarySearch(sorted, target, point, begin, mid);
        } else {
            return binarySearch(sorted, target, point, mid, end);
        }
    }

    private static class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        double calcDeg(final Point p) {
            final long x2 = p.x - this.x;
            final long y2 = p.y - this.y;
            return Math.toDegrees(Math.atan2(y2, x2));
        }

        @Override
        public int hashCode() {
            return x * 1_000_000_001 + y;
        }

        @Override
        public boolean equals(final Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            final Point casted = (Point) obj;
            return casted.x == this.x && casted.y == this.y;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    