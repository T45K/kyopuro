package AtCoder.other.typecal90.AQ043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final Point r = new Point(scanner.nextInt(), scanner.nextInt());
        final Point c = new Point(scanner.nextInt(), scanner.nextInt());
        final boolean[][] table = new boolean[h + 1][w + 1];
        for (int i = 1; i <= h; i++) {
            final String s = scanner.next();
            for (int j = 1; j <= w; j++) {
                table[i][j] = s.charAt(j - 1) == '.';
            }
        }

        System.out.println(dijkstra(h, w, table, r, c));
    }

    private static int dijkstra(final int h, final int w, final boolean[][] table, final Point r, final Point c) {
        final int[][] distances = new int[h + 1][w + 1];
        for (final int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE / 2);
        }
        distances[r.s][r.t] = -1;

        final PriorityQueue<Destination> queue = new PriorityQueue<>(Comparator.comparingInt(d -> d.cost));
        queue.add(new Destination(r, -1));

        while (!queue.isEmpty()) {
            final Destination poll = queue.poll();
            final Point point = poll.point;
            if (poll.cost > distances[point.s][point.t]) {
                continue;
            }
            final int cost = poll.cost + 1;
            for (int i = point.s - 1; i > 0; i--) {
                if (distances[i][point.t] < cost || !table[i][point.t]) {
                    break;
                }
                distances[i][point.t] = cost;
                queue.add(new Destination(new Point(i, point.t), cost));
            }

            for (int i = point.s + 1; i <= h; i++) {
                if (distances[i][point.t] < cost || !table[i][point.t]) {
                    break;
                }
                distances[i][point.t] = cost;
                queue.add(new Destination(new Point(i, point.t), cost));
            }

            for (int i = point.t - 1; i > 0; i--) {
                if (distances[point.s][i] < cost || !table[point.s][i]) {
                    break;
                }
                distances[point.s][i] = cost;
                queue.add(new Destination(new Point(point.s, i), cost));
            }

            for (int i = point.t + 1; i <= w; i++) {
                if (distances[point.s][i] < cost || !table[point.s][i]) {
                    break;
                }
                distances[point.s][i] = cost;
                queue.add(new Destination(new Point(point.s, i), cost));
            }
        }
        return distances[c.s][c.t];
    }

    private static class Destination {
        final Point point;
        final int cost;

        Destination(final Point point, final int cost) {
            this.point = point;
            this.cost = cost;
        }
    }

    private static class Point {
        final int s;
        final int t;

        Point(final int s, final int t) {
            this.s = s;
            this.t = t;
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
