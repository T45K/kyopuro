package AGC.AGC044.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import static java.lang.Math.min;

/*
座標
N<=500に対して，与えられる座標の数はN^2なので，座標が与えられる度に[d|b]fsすると間に合わないように見える
しかし，実は間に合う
1回の[b|d]fsでいくつかの座標の端からの距離が減る
では全ての走査で行われる操作は合計でO(n^3)で収まる
例えばN=6の時，最初の各座標の端からの距離は以下のようになる

0 0 0 0 0 0
0 1 1 1 1 0
0 1 2 2 1 0
0 1 2 2 1 0
0 1 1 1 1 0
0 0 0 0 0 0

全てを走査しきる（= 各座標の端からの距離が0になる）のは 4*4 + 2*2(四角形を狭めていくイメージ)
合計するとだいたい N^3/6 らしいので条件下で間に合う
 */
public class Main {
    private static final int[] xMove = {-1, 0, 1, 0};
    private static final int[] yMove = {0, -1, 0, 1};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        final int[][] distances = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                distances[i][j] = min(min(i, n - i - 1), min(j - 1, n - j));
            }
        }

        final boolean[][] hasGone = new boolean[n][n + 1];
        final UnaryOperator<Integer> bfs = point -> {
            final int x = (point - 1) / n;
            final int y = (point - 1) % n + 1;
            final int distance = distances[x][y];
            hasGone[x][y] = true;

            final Deque<Point> queue = new ArrayDeque<>();
            queue.add(new Point(x, y));
            while (!queue.isEmpty()) {
                final Point current = queue.pollFirst();
                for (int i = 0; i < 4; i++) {
                    final int nextX = current.x + xMove[i];
                    final int nextY = current.y + yMove[i];
                    if (nextX == -1 || nextX == n || nextY == 0 || nextY == n + 1) {
                        continue;
                    }

                    final int nextDistance = distances[current.x][current.y] + (hasGone[current.x][current.y] ? 0 : 1);
                    if (nextDistance < distances[nextX][nextY]) {
                        queue.add(new Point(nextX, nextY));
                        distances[nextX][nextY] = nextDistance;
                    }
                }
            }

            return distance;
        };

        final long count = IntStream.range(0, n * n)
            .map(i -> bfs.apply(scanner.nextInt()))
            .sum();

        System.out.println(count);
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
