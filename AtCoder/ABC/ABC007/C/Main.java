package AtCoder.ABC.ABC007.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Main {
    private static final int[] xMove = {-1, 0, 1, 0};
    private static final int[] yMove = {0, -1, 0, 1};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int r = scanner.nextInt();
        final int c = scanner.nextInt();
        final Point start = new Point(scanner.nextInt(), scanner.nextInt());
        final Point dest = new Point(scanner.nextInt(), scanner.nextInt());
        final boolean[][] table = new boolean[r + 2][c + 2];
        for (int i = 1; i <= r; i++) {
            final String s = scanner.next();
            for (int j = 1; j <= c; j++) {
                table[i][j] = s.charAt(j - 1) == '.';
            }
        }

        final int[][] distances = new int[r + 1][c + 1];
        final Function<Point, Boolean> isMovable = point -> table[point.x][point.y] && distances[point.x][point.y] == 0;

        final Deque<Point> queue = new ArrayDeque<>();
        queue.add(start);
        while (true) {
            final Point poll = Optional.ofNullable(queue.poll()).orElseThrow();
            if (poll.x == dest.x && poll.y == dest.y) {
                System.out.println(distances[dest.x][dest.y]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                final Point next = new Point(poll.x + xMove[i], poll.y + yMove[i]);
                if (isMovable.apply(next)) {
                    distances[next.x][next.y] = distances[poll.x][poll.y] + 1;
                    queue.add(next);
                }
            }
        }
    }

    private static class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
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
    }
}
