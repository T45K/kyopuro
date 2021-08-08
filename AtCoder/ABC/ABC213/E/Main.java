package AtCoder.ABC.ABC213.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

// TODO
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = s.charAt(j) == '.';
            }
        }
        final int[][] costs = new int[h][w];
        for (final int[] array : costs) {
            Arrays.fill(array, Integer.MAX_VALUE / 2);
        }

        final BiFunction<Integer, Integer, Boolean> choosable = (x, y) -> x >= 0 && x < h && y >= 0 && y < w;
        final PriorityQueue<Destination> queue = new PriorityQueue<>(Comparator.comparingInt(destination -> destination.cost));
        queue.add(new Destination(0, 0, 0));
        costs[0][0] = 0;
        while (!queue.isEmpty()) {
            final Destination poll = queue.poll();
            final int x = poll.x;
            final int y = poll.y;
            if (poll.cost != costs[x][y]) {
                continue;
            }
            if (x == h - 1 && y == w - 1) {
                break;
            }

            if (x > 0) {
                if (table[x - 1][y] && costs[x - 1][y] > poll.cost) {
                    queue.add(new Destination(x - 1, y, poll.cost));
                    costs[x - 1][y] = poll.cost;
                }
                if (!table[x - 1][y]) {
                    for (int yMove = -1; yMove <= 1; yMove++) {
                        if (choosable.apply(x - 2, y + yMove) &&
                            costs[x - 2][y + yMove] > poll.cost + 1) {
                            queue.add(new Destination(x - 2, y + yMove, poll.cost + 1));
                            costs[x - 2][y + yMove] = poll.cost + 1;
                        }
                        if (choosable.apply(x - 1, y + yMove) &&
                            costs[x - 1][y + yMove] > poll.cost + 1) {
                            queue.add(new Destination(x - 1, y + yMove, poll.cost + 1));
                            costs[x - 1][y + yMove] = poll.cost + 1;
                        }
                    }
                }
            }

            if (x < h - 1) {
                if (table[x + 1][y] && costs[x + 1][y] > poll.cost) {
                    queue.add(new Destination(x + 1, y, poll.cost));
                    costs[x + 1][y] = poll.cost;
                }
                if (!table[x + 1][y]) {
                    for (int yMove = -1; yMove <= 1; yMove++) {
                        if (choosable.apply(x + 2, y + yMove) &&
                            costs[x + 2][y + yMove] > poll.cost + 1) {
                            queue.add(new Destination(x + 2, y + yMove, poll.cost + 1));
                            costs[x + 2][y + yMove] = poll.cost + 1;
                        }
                        if (choosable.apply(x + 1, y + yMove) &&
                            costs[x + 1][y + yMove] > poll.cost + 1) {
                            queue.add(new Destination(x + 1, y + yMove, poll.cost + 1));
                            costs[x + 1][y + yMove] = poll.cost + 1;
                        }
                    }
                }
            }

            if (y > 0) {
                if (table[x][y - 1] && costs[x][y - 1] > poll.cost) {
                    queue.add(new Destination(x, y - 1, poll.cost));
                    costs[x][y - 1] = poll.cost;
                }
                if (!table[x][y - 1]) {
                    for (int xMove = -1; xMove <= 1; xMove++) {
                        if (choosable.apply(x + xMove, y - 1) &&
                            costs[x + xMove][y - 1] > poll.cost + 1) {
                            queue.add(new Destination(x + xMove, y - 1, poll.cost + 1));
                            costs[x + xMove][y - 1] = poll.cost + 1;
                        }
                        if (choosable.apply(x + xMove, y - 2) &&
                            costs[x + xMove][y - 2] > poll.cost + 1) {
                            queue.add(new Destination(x + xMove, y - 2, poll.cost + 1));
                            costs[x + xMove][y - 2] = poll.cost + 1;
                        }
                    }
                }
            }

            if (y < w - 1) {
                if (table[x][y + 1] && costs[x][y + 1] > poll.cost) {
                    queue.add(new Destination(x, y + 1, poll.cost));
                    costs[x][y + 1] = poll.cost;
                }
                if (!table[x][y + 1]) {
                    for (int xMove = -1; xMove <= 1; xMove++) {
                        if (choosable.apply(x + xMove, y + 1) &&
                            costs[x + xMove][y + 1] > poll.cost + 1) {
                            queue.add(new Destination(x + xMove, y + 1, poll.cost + 1));
                            costs[x + xMove][y + 1] = poll.cost + 1;
                        }
                        if (choosable.apply(x + xMove, y + 2) &&
                            costs[x + xMove][y + 2] > poll.cost + 1) {
                            queue.add(new Destination(x + xMove, y + 2, poll.cost + 1));
                            costs[x + xMove][y + 2] = poll.cost + 1;
                        }
                    }
                }
            }
        }
        System.out.println(costs[h - 1][w - 1]);
    }

    private static class Destination {
        final int x;
        final int y;
        final int cost;

        Destination(final int x, final int y, final int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
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
