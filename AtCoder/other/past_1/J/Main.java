package AtCoder.other.past_1.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
3点ダイクストラ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final long[][] table = new long[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final long[][] leftBottom = dijkstra(table, h - 1, 0, h, w);
        final long[][] rightBottom = dijkstra(table, h - 1, w - 1, h, w);
        final long[][] rightUp = dijkstra(table, 0, w - 1, h, w);

        long min = Long.MAX_VALUE;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                min = Math.min(min, leftBottom[i][j] + rightBottom[i][j] + rightUp[i][j] - 2 * table[i][j]);
            }
        }

        System.out.println(min);
    }

    private static long[][] dijkstra(final long[][] table, final int i, final int j, final int h, final int w) {
        final long[][] result = new long[h][w];
        for (final long[] array : result) {
            Arrays.fill(array, Long.MAX_VALUE);
        }
        result[i][j] = 0;
        final PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingLong(point -> point.cost));
        if (i > 0) {
            result[i - 1][j] = table[i - 1][j];
            queue.add(new Point(i - 1, j, table[i - 1][j]));
        }
        if (i < h - 1) {
            result[i + 1][j] = table[i + 1][j];
            queue.add(new Point(i + 1, j, table[i + 1][j]));
        }
        if (j > 0) {
            result[i][j - 1] = table[i][j - 1];
            queue.add(new Point(i, j - 1, table[i][j - 1]));
        }
        if (j < w - 1) {
            result[i][j + 1] = table[i][j + 1];
            queue.add(new Point(i, j + 1, table[i][j + 1]));
        }

        while (!queue.isEmpty()) {
            final Point poll = queue.poll();
            final int tmpI = poll.i;
            final int tmpJ = poll.j;
            final long tmpCost = poll.cost;
            if (tmpCost > result[tmpI][tmpJ]) {
                continue;
            }
            result[tmpI][tmpJ] = tmpCost;
            if (tmpI > 0 && tmpCost + table[tmpI - 1][tmpJ] < result[tmpI - 1][tmpJ]) {
                result[tmpI - 1][tmpJ] = tmpCost + table[tmpI - 1][tmpJ];
                queue.add(new Point(tmpI - 1, tmpJ, tmpCost + table[tmpI - 1][tmpJ]));
            }
            if (tmpI < h - 1 && tmpCost + table[tmpI + 1][tmpJ] < result[tmpI + 1][tmpJ]) {
                result[tmpI + 1][tmpJ] = tmpCost + table[tmpI + 1][tmpJ];
                queue.add(new Point(tmpI + 1, tmpJ, tmpCost + table[tmpI + 1][tmpJ]));
            }
            if (tmpJ > 0 && tmpCost + table[tmpI][tmpJ - 1] < result[tmpI][tmpJ - 1]) {
                result[tmpI][tmpJ - 1] = tmpCost + table[tmpI][tmpJ - 1];
                queue.add(new Point(tmpI, tmpJ - 1, tmpCost + table[tmpI][tmpJ - 1]));
            }
            if (tmpJ < w - 1 && tmpCost + table[tmpI][tmpJ + 1] < result[tmpI][tmpJ + 1]) {
                result[tmpI][tmpJ + 1] = tmpCost + table[tmpI][tmpJ + 1];
                queue.add(new Point(tmpI, tmpJ + 1, tmpCost + table[tmpI][tmpJ + 1]));
            }
        }
        return result;
    }

    final static class Point {
        final int i;
        final int j;
        final long cost;

        Point(final int i, final int j, final long cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
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

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
