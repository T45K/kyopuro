package AtCoder.ABC.ABC176.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

public class Main {
    private static final int[] walkX = {1, 0, -1, 0};
    private static final int[] walkY = {0, 1, 0, -1};
    private static final int[] warp = {-2, -1, 0, 1, 2};

    /*
    徒歩がコスト0，ワープがコスト1のダイクストラ
     */
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final BiFunction<Integer, Integer, Boolean> isInside = (x, y) -> x >= 0 && x < h && y >= 0 && y < w;

        final int sx = scanner.nextInt() - 1;
        final int sy = scanner.nextInt() - 1;
        final int dx = scanner.nextInt() - 1;
        final int dy = scanner.nextInt() - 1;

        final int[][] table = new int[h][w];
        for (final int[] array : table) {
            Arrays.fill(array, -1);
        }
        table[sx][sy] = 0;
        final boolean[][] field = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                field[i][j] = s.charAt(j) == '.';
            }
        }

        final PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(p -> table[p.x][p.y]));
        queue.add(new Point(sx, sy));
        while (!queue.isEmpty()) {
            final Point poll = Optional.ofNullable(queue.poll()).orElseThrow();
            for (int i = 0; i < 4; i++) {
                final int nextX = poll.x + walkX[i];
                final int nextY = poll.y + walkY[i];
                if (!isInside.apply(nextX, nextY)) {
                    continue;
                }

                if (!field[nextX][nextY]) {
                    continue;
                }

                if (table[nextX][nextY] == -1 || table[nextX][nextY] > table[poll.x][poll.y]) {
                    table[nextX][nextY] = table[poll.x][poll.y];
                    queue.add(new Point(nextX, nextY));
                }
            }

            for (final int warpX : warp) {
                final int nextX = poll.x + warpX;
                for (final int warpY : warp) {
                    final int nextY = poll.y + warpY;
                    if (!isInside.apply(nextX, nextY)) {
                        continue;
                    }

                    if (!field[nextX][nextY]) {
                        continue;
                    }

                    if (table[nextX][nextY] == -1 || table[nextX][nextY] > table[poll.x][poll.y] + 1) {
                        table[nextX][nextY] = table[poll.x][poll.y] + 1;
                        queue.add(new Point(nextX, nextY));
                    }
                }
            }
        }

        System.out.println(table[dx][dy]);
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
