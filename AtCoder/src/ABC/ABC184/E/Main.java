package ABC.ABC184.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
ダイクストラ
ある文字でのテレポートはたかだか一回しか行われない
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] canEnter = new boolean[h][w];
        final char[][] teleporter = new char[h][w];
        for (final char[] array : teleporter) {
            Arrays.fill(array, '0');
        }
        final Map<Character, List<Cell>> map = new HashMap<>();
        Cell s = null;
        Cell g = null;
        for (int i = 0; i < h; i++) {
            final String line = scanner.next();
            for (int j = 0; j < w; j++) {
                final char c = line.charAt(j);
                if (c == '#') {
                    continue;
                }
                canEnter[i][j] = true;
                if (c == 'S') {
                    s = new Cell(i, j);
                }
                if (c == 'G') {
                    g = new Cell(i, j);
                }
                if (c >= 'a' && c <= 'z') {
                    map.computeIfAbsent(c, v -> new ArrayList<>()).add(new Cell(i, j));
                    teleporter[i][j] = c;
                }
            }
        }
        assert s != null && g != null;
        final int[][] distances = new int[h][w];
        for (final int[] array : distances) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        distances[s.x][s.y] = 0;

        final boolean[] isUsed = new boolean[26];
        final PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(c -> distances[c.x][c.y]));
        queue.add(s);
        while (!queue.isEmpty()) {
            final Cell poll = queue.poll();
            final int x = poll.x;
            final int y = poll.y;
            if (x == g.x && y == g.y) {
                break;
            }
            final int base = distances[x][y];
            if (x > 0 && canEnter[x - 1][y] && distances[x - 1][y] > base + 1) {
                distances[x - 1][y] = base + 1;
                queue.add(new Cell(x - 1, y));
            }
            if (x < h - 1 && canEnter[x + 1][y] && distances[x + 1][y] > base + 1) {
                distances[x + 1][y] = base + 1;
                queue.add(new Cell(x + 1, y));
            }
            if (y > 0 && canEnter[x][y - 1] && distances[x][y - 1] > base + 1) {
                distances[x][y - 1] = base + 1;
                queue.add(new Cell(x, y - 1));
            }
            if (y < w - 1 && canEnter[x][y + 1] && distances[x][y + 1] > base + 1) {
                distances[x][y + 1] = base + 1;
                queue.add(new Cell(x, y + 1));
            }
            final char teleport = teleporter[x][y];
            if (teleport != '0' && !isUsed[teleport - 'a']) {
                isUsed[teleport - 'a'] = true;
                for (final Cell cell : map.get(teleport)) {
                    if (cell.x == x && cell.y == y) {
                        continue;
                    }
                    if (distances[cell.x][cell.y] > base + 1) {
                        distances[cell.x][cell.y] = base + 1;
                        queue.add(new Cell(cell.x, cell.y));
                    }
                }
            }
        }

        if (distances[g.x][g.y] < Integer.MAX_VALUE) {
            System.out.println(distances[g.x][g.y]);
        } else {
            System.out.println(-1);
        }
    }

    private static class Cell {
        final int x;
        final int y;

        Cell(final int x, final int y) {
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
