package other.past_3.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static final int[] xMove = {1, 0, -1, 1, -1, 0};
    private static final int[] yMove = {1, 1, 1, 0, 0, -1};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();

        if (x == 0 && y == 0) {
            System.out.println(0);
            return;
        }

        final int[][] table = new int[403][403];
        for (int i = 0; i < n; i++) {
            final int xi = scanner.nextInt();
            final int yi = scanner.nextInt();

            table[xi + 201][yi + 201] = -1;
        }

        final Point initial = new Point(201, 201);
        table[201][201] = 0;
        final Deque<Point> queue = new ArrayDeque<>();
        queue.add(initial);

        while (!queue.isEmpty()) {
            final Point current = queue.poll();
            final int distance = table[current.x][current.y];
            for (int i = 0; i < 6; i++) {
                final int nextX = current.x + xMove[i];
                final int nextY = current.y + yMove[i];
                if (nextX == x + 201 && nextY == y + 201) {
                    System.out.println(distance + 1);
                    return;
                }
                if (nextX == 201 && nextY == 201) {
                    continue;
                }
                if (nextX <= 402 && nextX >= 0 && nextY <= 402 && nextY >= 0 && table[nextX][nextY] == 0) {
                    table[nextX][nextY] = distance + 1;
                    queue.add(new Point(nextX, nextY));
                }
            }
        }

        System.out.println(-1);
    }

    private static class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
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

            final Point point = (Point) obj;
            return this.x == point.x && this.y == point.y;
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
