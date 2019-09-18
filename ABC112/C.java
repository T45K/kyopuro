package ABC112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class C {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        int min = Integer.MAX_VALUE;

        final List<Point> points = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            final int h = scanner.nextInt();

            min = Math.min(h, min);
            points.add(new Point(x, y, h));
        }

        for (int x = 0; x <= 100; x++) {
            for (int y = 0; y <= 100; y++) {
                int h;
                for (h = min; h <= min + 200; h++) {
                    boolean flag = true;
                    for (final Point point : points) {
                        if (point.getH() != Math.max(0, h - Math.abs(x - point.getX()) - Math.abs(y - point.getY()))) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        System.out.println(x + " " + y + " " + h);
                        return;
                    }
                }
            }
        }

    }

    static class Point {
        private final int x;
        private final int y;
        private final int h;

        Point(final int x, final int y, final int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        int getH() {
            return h;
        }
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
