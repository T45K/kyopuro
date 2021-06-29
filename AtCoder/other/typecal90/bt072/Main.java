package AtCoder.other.typecal90.bt072;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String next = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = next.charAt(j) == '.';
            }
        }

        final int answer = IntStream.range(0, h)
            .flatMap(i -> IntStream.range(0, w)
                .filter(j -> table[i][j])
                .map(j -> new Solver(h, w, table).solve(i, j))
            )
            .filter(i -> i >= 4)
            .max()
            .orElse(-1);
        System.out.println(answer);
    }

    private static class Solver {
        private static final int[] X_MOVE = {-1, 0, 1, 0};
        private static final int[] Y_MOVE = {0, -1, 0, 1};

        private final int h;
        private final int w;
        private final boolean[][] table;

        public Solver(final int h, final int w, final boolean[][] table) {
            this.h = h;
            this.w = w;
            this.table = table;
        }

        public int solve(final int x, final int y) {
            final int start = flat(x, y);
            final int state = 1 << start;
            return recursive(x, y, state, start);
        }

        private int recursive(final int x, final int y, final int state, final int initial) {
            int max = -1;
            for (int i = 0; i < 4; i++) {
                final int xNext = x + X_MOVE[i];
                final int yNext = y + Y_MOVE[i];
                if (!isMovable(xNext, yNext)) {
                    continue;
                }

                final int next = flat(xNext, yNext);
                if (initial == next) {
                    max = Math.max(max, countOne(state));
                    continue;
                }

                final int bit = 1 << next;
                if ((state & bit) > 0) {
                    continue;
                }

                max = Math.max(max, recursive(xNext, yNext, state | bit, initial));
            }
            return max;
        }

        private int countOne(final int value) {
            final String bit = Integer.toBinaryString(value);
            return (int) IntStream.range(0, bit.length())
                .mapToObj(bit::charAt)
                .filter(c -> c == '1')
                .count();
        }

        private boolean isMovable(final int x, final int y) {
            return x >= 0 && x < h && y >= 0 && y < w && table[x][y];
        }

        private int flat(final int x, final int y) {
            return x * w + y;
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
