package AtCoder.ABC.ABC037.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
コストの大きい順に処理していく 計算量はO(H * W * log(H * W)) (H * W <= 10 ^ 6)
 */
public class MainAlt {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final int[][] table = new int[h][w];
        final List<Cell> list = new ArrayList<>(h * w);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                final int a = scanner.nextInt();
                table[i][j] = a;
                list.add(new Cell(i, j, a));
            }
        }

        final long[][] costs = new long[h][w];
        list.sort(Comparator.comparingInt(cell -> -cell.cost));
        for (final Cell cell : list) {
            long sum = 1;
            final int i = cell.i;
            final int j = cell.j;
            if (i > 0 && table[i][j] < table[i - 1][j]) {
                sum += costs[i - 1][j];
                sum %= MOD;
            }
            if (i < h - 1 && table[i][j] < table[i + 1][j]) {
                sum += costs[i + 1][j];
                sum %= MOD;
            }
            if (j > 0 && table[i][j] < table[i][j - 1]) {
                sum += costs[i][j - 1];
                sum %= MOD;
            }
            if (j < w - 1 && table[i][j] < table[i][j + 1]) {
                sum += costs[i][j + 1];
                sum %= MOD;
            }
            costs[i][j] = sum;
        }

        long answer = 0;
        for (final long[] array : costs) {
            for (final long value : array) {
                answer += value;
                answer %= MOD;
            }
        }
        System.out.println(answer);
    }

    private static class Cell {
        final int i;
        final int j;
        final int cost;

        Cell(final int i, final int j, final int cost) {
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
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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

