package AtCoder.other.zone2021.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
ダイクストラ
 */
public class Main {
    public static void main(final String[] args) throws IOException {
        final FastScanner scanner = new FastScanner(System.in);
        final int r = scanner.nextInt();
        final int c = scanner.nextInt();
        final long[][] aTable = new long[r + 1][c];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j < c; j++) {
                aTable[i][j] = scanner.nextInt();
            }
        }
        final long[][] bTable = new long[r][c + 1];
        for (int i = 1; i < r; i++) {
            for (int j = 1; j <= c; j++) {
                bTable[i][j] = scanner.nextInt();
            }
        }
        final long answer = dijkstra(r, c, aTable, bTable);
        System.out.println(answer);
    }

    private static long dijkstra(final int r, final int c, final long[][] aTable, final long[][] bTable) {
        final long[][] costs = new long[r + 1][c + 1];
        for (final long[] cost : costs) {
            Arrays.fill(cost, Long.MAX_VALUE);
        }
        final PriorityQueue<Dest> queue = new PriorityQueue<>(Comparator.comparingLong(dest -> costs[dest.x][dest.y]));
        costs[1][2] = aTable[1][1];
        costs[2][1] = bTable[1][1];
        queue.add(new Dest(1, 2));
        queue.add(new Dest(2, 1));
        while (!queue.isEmpty()) {
            final Dest poll = queue.poll();
            final int x = poll.x;
            final int y = poll.y;
            if (y < c && costs[x][y] + aTable[x][y] < costs[x][y + 1]) {
                costs[x][y + 1] = costs[x][y] + aTable[x][y];
                queue.add(new Dest(x, y + 1));
            }
            if (y > 1 && costs[x][y] + aTable[x][y - 1] < costs[x][y - 1]) {
                costs[x][y - 1] = costs[x][y] + aTable[x][y - 1];
                queue.add(new Dest(x, y - 1));
            }
            if (x < r && costs[x][y] + bTable[x][y] < costs[x + 1][y]) {
                costs[x + 1][y] = costs[x][y] + bTable[x][y];
                queue.add(new Dest(x + 1, y));
            }
            for (int i = 1; x - i > 0; i++) {
                if (costs[x][y] + i + 1 > costs[x - i][y]) {
                    break;
                }
                costs[x - i][y] = costs[x][y] + i + 1;
                queue.add(new Dest(x - i, y));
            }
        }
        return costs[r][c];
    }

    private static class Dest {
        final int x;
        final int y;

        public Dest(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = new StringTokenizer(reader.readLine());
        }

        String next() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
    