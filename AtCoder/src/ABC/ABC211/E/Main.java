package ABC.ABC211.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
解説AC
盤面の全ての状態を記録しておく
 */
public class Main {
    private static final int WHITE = 0;
    private static final int BLACK = 1;
    private static final int RED = 2;

    private static final int[] xMove = {-1, 0, 1, 0};
    private static final int[] yMove = {0, -1, 0, 1};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            final String s = scanner.next();
            for (int j = 0; j < n; j++) {
                table[i][j] = s.charAt(j) == '.' ? WHITE : BLACK;
            }
        }

        System.out.println(calc(n, table, k));
    }

    private static int calc(final int n, final int[][] table, final int k) {
        final Set<ArrayWrapper> set = new HashSet<>();
        final Function<Integer, Integer> dfs = new Function<>() {
            @Override
            public Integer apply(final Integer rest) {
                final ArrayWrapper arrayWrapper = new ArrayWrapper(table);
                if (set.contains(arrayWrapper)) {
                    return 0;
                }

                set.add(arrayWrapper);
                if (rest == 0) {
                    return 1;
                }

                int count = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (isWhiteNextToRed(i, j, table)) {
                            table[i][j] = RED;
                            count += this.apply(rest - 1);
                            table[i][j] = WHITE;
                        }
                    }
                }
                return count;
            }
        };

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == BLACK) {
                    continue;
                }

                table[i][j] = RED;
                count += dfs.apply(k - 1);
                table[i][j] = WHITE;
            }
        }
        return count;
    }

    private static boolean isWhiteNextToRed(final int i, final int j, final int[][] table) {
        if (table[i][j] != WHITE) {
            return false;
        }
        for (int k = 0; k < 4; k++) {
            if (isRed(i + xMove[k], j + yMove[k], table)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRed(final int i, final int j, final int[][] table) {
        if (i < 0 || i >= table.length || j < 0 || j >= table.length) {
            return false;
        }

        return table[i][j] == RED;
    }

    private static class ArrayWrapper {
        final int[] table;

        ArrayWrapper(final int[][] table) {
            this.table = new int[table.length];
            for (int i = 0; i < table.length; i++) {
                int base = 1;
                int sum = 0;
                for (int j = 0; j < table.length; j++) {
                    sum += table[i][j] * base;
                    base *= 3;
                }
                this.table[i] = sum;
            }
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final ArrayWrapper that = (ArrayWrapper) o;
            return Arrays.equals(table, that.table);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(table);
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
