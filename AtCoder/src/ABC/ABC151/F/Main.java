package ABC.ABC151.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

// TODO solve 外心ではないらしい
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Point> list = IntStream.range(0, n)
                .mapToObj(i -> new Point(scanner.nextDouble(), scanner.nextDouble()))
                .collect(Collectors.toList());

        if (list.size() == 2) {
            final double distance = calcDistance(list.get(0), list.get(1));
            System.out.println(distance / 2d);
            return;
        }

        double min = Double.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            final Point a = list.get(i);
            for (int j = i + 1; j < n - 1; j++) {
                final Point b = list.get(j);
                for (int k = j + 1; k < n; k++) {
                    final Point c = list.get(k);
                    final Point outerCenter = calcOuterCenter(a, b, c);
                    final double max = list.stream()
                            .mapToDouble(d -> sqrt(square(d.x - outerCenter.x) + square(d.y - outerCenter.y)))
                            .max()
                            .orElseThrow(RuntimeException::new);
                    min = min(min, max);
                }
            }
        }

        System.out.println(min);
    }

    private static Point calcOuterCenter(final Point a, final Point b, final Point c) {
        final double sin2A = sin(2d * acos(a, b, c));
        final double sin2B = sin(2d * acos(b, c, a));
        final double sin2C = sin(2d * acos(c, a, b));

        if (sin2A == 0d && sin2B == 0d && sin2C == 0d) {
            final double distanceAB = calcDistance(a, b);
            final double distanceBC = calcDistance(b, c);
            final double distanceAC = calcDistance(a, c);

            if (distanceAB > Math.max(distanceBC, distanceAC)) {
                return new Point((a.x + b.x) / 2d, (a.y + b.y) / 2d);
            } else if (distanceBC > Math.max(distanceAB, distanceAC)) {
                return new Point((b.x + c.x) / 2d, (b.y + c.y) / 2d);
            } else {
                return new Point((a.x + c.x) / 2d, (a.y + c.y) / 2d);
            }
        }

        final double x = (sin2A * a.x + sin2B * b.x + sin2C * c.x) / (sin2A + sin2B + sin2C);
        final double y = (sin2A * a.y + sin2B * b.y + sin2C * c.y) / (sin2A + sin2B + sin2C);

        return new Point(x, y);
    }

    private static double acos(final Point a, final Point b, final Point c) {
        final double vec1x = b.x - a.x;
        final double vec1y = b.y - a.y;
        final double vec2x = c.x - a.x;
        final double vec2y = c.y - a.y;
        final double distanceAB = sqrt(square(vec1x) + square(vec1y));
        final double distanceAC = sqrt(square(vec2x) + square(vec2y));
        final double cos = (vec1x * vec2x + vec1y * vec2y) / (distanceAB * distanceAC);
        if (cos > 1d || cos < -1d) {
            return 0d;
        }
        return Math.acos(cos);
    }

    private static double calcDistance(final Point a, final Point b) {
        return sqrt(square(a.x - b.x) + square(a.y - b.y));
    }

    private static double square(final double a) {
        return pow(a, 2);
    }

    private static class Point {
        final double x;
        final double y;

        Point(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
    