package AtCoder.other.typecal90.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// TODO WIP
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Point[] array = new Point[n];
        final Point[] sorted = new Point[n];
        for (int i = 0; i < n; i++) {
            final Point point = new Point(scanner.nextInt(), scanner.nextInt());
            array[i] = point;
            sorted[i] = point;
        }
        double max = 0;
        for (final Point point : array) {
            Arrays.sort(sorted, Comparator.comparingDouble(point::calcDeg));
            final boolean[] visited = new boolean[n];
            for (int i = 0; i < sorted.length; i++) {
                final Point tmp = sorted[i];
                if (point.equals(tmp) || visited[i]) {
                    continue;
                }
                final double deg = point.calcDeg(tmp);
                final double opposite = Math.abs(deg - 180);
                if (opposite < point.calcDeg(sorted[0]) || opposite > point.calcDeg(sorted[sorted.length - 1])) {
                    final double a = point.calcDeg(tmp, sorted[0]);
                    final double b = point.calcDeg(tmp, sorted[sorted.length - 1]);
                    if (a > b) {
                        visited[0] = true;
                        max = Math.max(max, a);
                    } else {
                        visited[sorted.length - 1] = true;
                        max = Math.max(max, b);
                    }
                    continue;
                }
                final int index = binarySearch(sorted, opposite, point, 0, sorted.length - 1);
                final double a = point.calcDeg(tmp, sorted[index]);
                final double b = point.calcDeg(tmp, sorted[index - 1]);
                if (a > b) {
                    visited[index] = true;
                    max = Math.max(max, a);
                } else {
                    visited[index - 1] = true;
                    max = Math.max(max, b);
                }
            }
        }
        System.out.println(max);
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
            final double cos = Math.acos(x2 / Math.sqrt(x2 * x2 + y2 * y2));
            return Math.toDegrees(cos);
        }

        double calcDeg(final Point a, final Point b) {
            if (a.equals(b) || this.equals(a) || this.equals(b)) {
                return 0;
            }
            final long x1 = a.x - this.x;
            final long y1 = a.y - this.y;
            final long x2 = b.x - this.x;
            final long y2 = b.y - this.y;
            final double cos = Math.acos((x1 * x2 + y1 * y2) / (Math.sqrt(x1 * x1 + y1 * y1) * Math.sqrt(x2 * x2 + y2 * y2)));
            return Math.toDegrees(cos);
        }

        @Override
        public int hashCode() {
            return x + y;
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
    