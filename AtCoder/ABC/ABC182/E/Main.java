package AtCoder.ABC.ABC182.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final int NONE = 0;
    private static final int LIGHT = 1;
    private static final int BLOCK = 2;
    private static final int FROM_LEFT = 3;
    private static final int FROM_RIGHT = 5;
    private static final int FROM_UPPER = 7;
    private static final int FROM_DOWN = 11;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final List<Pair> lights = IntStream.range(0, n)
            .mapToObj(i -> new Pair(scanner.nextInt() - 1, scanner.nextInt() - 1))
            .collect(Collectors.toList());
        final List<Pair> blocks = IntStream.range(0, m)
            .mapToObj(i -> new Pair(scanner.nextInt() - 1, scanner.nextInt() - 1))
            .collect(Collectors.toList());

        final int[][] table = new int[h][w];
        lights.forEach(light -> table[light.i][light.j] = LIGHT);
        blocks.forEach(block -> table[block.i][block.j] = BLOCK);

        // 左上から右下へ
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (table[i][j] == NONE || table[i][j] == BLOCK) {
                    continue;
                }

                if (table[i][j] == LIGHT) {
                    // 電灯の右側
                    if (i < h - 1 && (table[i + 1][j] != BLOCK && table[i + 1][j] != LIGHT)) {
                        if (table[i + 1][j] == 0) {
                            table[i + 1][j] += FROM_LEFT;
                        } else {
                            table[i + 1][j] *= FROM_LEFT;
                        }
                    }
                    // 電灯の下側
                    if (j < w - 1 && (table[i][j + 1] != BLOCK && table[i][j + 1] != LIGHT)) {
                        if (table[i][j + 1] == 0) {
                            table[i][j + 1] += FROM_UPPER;
                        } else {
                            table[i][j + 1] *= FROM_UPPER;
                        }
                    }
                }
                if (table[i][j] % FROM_LEFT == 0) {
                    if (i < h - 1 && (table[i + 1][j] != BLOCK && table[i + 1][j] != LIGHT)) {
                        if (table[i + 1][j] == 0) {
                            table[i + 1][j] += FROM_LEFT;
                        } else {
                            table[i + 1][j] *= FROM_LEFT;
                        }
                    }
                }
                if (table[i][j] % FROM_UPPER == 0) {
                    if (j < w - 1 && (table[i][j + 1] != BLOCK && table[i][j + 1] != LIGHT)) {
                        if (table[i][j + 1] == 0) {
                            table[i][j + 1] += FROM_UPPER;
                        } else {
                            table[i][j + 1] *= FROM_UPPER;
                        }
                    }
                }
            }
        }

        // 右下から左上へ
        for (int i = h - 1; i >= 0; i--) {
            for (int j = w - 1; j >= 0; j--) {
                if (table[i][j] == NONE || table[i][j] == BLOCK) {
                    continue;
                }

                if (table[i][j] == LIGHT) {
                    // 電灯の左側
                    if (i > 0 && (table[i - 1][j] != LIGHT && table[i - 1][j] != BLOCK)) {
                        if (table[i - 1][j] == 0) {
                            table[i - 1][j] += FROM_RIGHT;
                        } else {
                            table[i - 1][j] *= FROM_RIGHT;
                        }
                    }
                    // 電灯の上側
                    if (j > 0 && (table[i][j - 1] != BLOCK && table[i][j - 1] != LIGHT)) {
                        if (table[i][j - 1] == 0) {
                            table[i][j - 1] += FROM_DOWN;
                        } else {
                            table[i][j - 1] *= FROM_DOWN;
                        }
                    }
                }
                if (table[i][j] % FROM_RIGHT == 0) {
                    if (i > 0 && (table[i - 1][j] != LIGHT && table[i - 1][j] != BLOCK)) {
                        if (table[i - 1][j] == 0) {
                            table[i - 1][j] += FROM_RIGHT;
                        } else {
                            table[i - 1][j] *= FROM_RIGHT;
                        }
                    }
                }
                if (table[i][j] % FROM_DOWN == 0) {
                    if (j > 0 && (table[i][j - 1] != BLOCK && table[i][j - 1] != LIGHT)) {
                        if (table[i][j - 1] == 0) {
                            table[i][j - 1] += FROM_DOWN;
                        } else {
                            table[i][j - 1] *= FROM_DOWN;
                        }
                    }
                }
            }
        }

        final long nones = IntStream.range(0, h)
            .flatMap(i -> Arrays.stream(table[i], 0, w))
            .filter(value -> value == NONE)
            .count();
        System.out.println(h * w - nones - m);
    }

    private static class Pair {
        final int i;
        final int j;

        Pair(final int i, final int j) {
            this.i = i;
            this.j = j;
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
