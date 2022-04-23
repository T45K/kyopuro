package AtCoder.ABC.ABC246.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int ax = scanner.nextInt() - 1;
        final int ay = scanner.nextInt() - 1;
        final int bx = scanner.nextInt() - 1;
        final int by = scanner.nextInt() - 1;

        final int[][][] table = new int[2][n][n]; // 0: 左上 or 右下、1: 右上 or 左下
        for (int i = 0; i < n; i++) {
            final String s = scanner.next();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '#') {
                    table[0][i][j] = Integer.MAX_VALUE;
                    table[1][i][j] = Integer.MAX_VALUE;
                } else {
                    table[0][i][j] = -1;
                    table[1][i][j] = -1;
                }
            }
        }
        table[0][ax][ay] = 0;
        table[1][ax][ay] = 0;

        final Deque<BfsElement> queue = new ArrayDeque<>();
        queue.add(new BfsElement(ax, ay, 0));
        queue.add(new BfsElement(ax, ay, 1));
        while (!queue.isEmpty()) {
            final BfsElement poll = queue.poll();
            final int x = poll.x;
            final int y = poll.y;
            final int direction = poll.direction;
            final int count = table[1 - direction][x][y];
            if (direction == 0) { // 左上か右下へ移動していく -> 次は右上か左下に行く
                for (int i = 1; ; i++) {
                    if (x - i < 0 || y - i < 0 || table[0][x - i][y - i] >= 0) {
                        break;
                    }
                    table[0][x - i][y - i] = count + 1;
                    queue.add(new BfsElement(x - i, y - i, 1));
                }
                for (int i = 1; ; i++) {
                    if (x + i >= n || y + i >= n || table[0][x + i][y + i] >= 0) {
                        break;
                    }
                    table[0][x + i][y + i] = count + 1;
                    queue.add(new BfsElement(x + i, y + i, 1));
                }
            } else {
                for (int i = 1; ; i++) {
                    if (x - i < 0 || y + i >= n || table[1][x - i][y + i] >= 0) {
                        break;
                    }
                    table[1][x - i][y + i] = count + 1;
                    queue.add(new BfsElement(x - i, y + i, 0));
                }
                for (int i = 1; ; i++) {
                    if (x + i >= n || y - i < 0 || table[1][x + i][y - i] >= 0) {
                        break;
                    }
                    table[1][x + i][y - i] = count + 1;
                    queue.add(new BfsElement(x + i, y - i, 0));
                }
            }
        }

        final int a = table[0][bx][by];
        final int b = table[1][bx][by];
        if (a == -1 && b == -1) {
            System.out.println(-1);
        } else if (a == -1 || b == -1) {
            System.out.println(Math.max(a, b));
        } else {
            System.out.println(Math.min(a, b));
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

    private static class BfsElement {
        final int x;
        final int y;
        final int direction;

        BfsElement(final int x, final int y, final int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
