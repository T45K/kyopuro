package AtCoder.ABC.ABC219.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final List<Pair> list = Stream.generate(() -> new Pair(Math.min(scanner.nextInt(), x), Math.min(scanner.nextInt(), y)))
            .limit(n)
            .collect(Collectors.toList());
        final int[][][] cube = new int[n][x + 1][y + 1];
        for (final int[][] table : cube) {
            for (final int[] array : table) {
                Arrays.fill(array, Integer.MAX_VALUE / 2);
            }
        }
        cube[0][0][0] = 0;
        cube[0][list.get(0).a][list.get(0).b] = 1;
        for (int i = 1; i < n; i++) {
            final Pair pair = list.get(i);
            for (int a = 0; a <= x; a++) {
                for (int b = 0; b <= y; b++) {
                    final int tmp = cube[i - 1][a][b];
                    if (tmp >= Integer.MAX_VALUE / 2) {
                        continue;
                    }
                    cube[i][a][b] = Math.min(cube[i][a][b], tmp);
                    if (a + pair.a >= x && b + pair.b >= y) {
                        cube[i][x][y] = Math.min(cube[i][x][y], tmp + 1);
                    } else if (a + pair.a >= x) {
                        cube[i][x][b + pair.b] = Math.min(cube[i][x][b + pair.b], tmp + 1);
                    } else if (b + pair.b >= y) {
                        cube[i][a + pair.a][y] = Math.min(cube[i][a + pair.a][y], tmp + 1);
                    } else {
                        cube[i][a + pair.a][b + pair.b] = Math.min(cube[i][a + pair.a][b + pair.b], tmp + 1);
                    }
                }
            }
        }
        if (cube[n - 1][x][y] == Integer.MAX_VALUE / 2) {
            System.out.println(-1);
        } else {
            System.out.println(cube[n - 1][x][y]);
        }
    }

    private static class Pair {
        final int a;
        final int b;

        Pair(final int a, final int b) {
            this.a = a;
            this.b = b;
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
