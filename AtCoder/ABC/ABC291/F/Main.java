package AtCoder.ABC.ABC291.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static final int INITIAL_VALUE = Integer.MAX_VALUE / 2;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[][] table = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            table[i] = new int[m + 1];
            final String s = scanner.next();
            for (int j = 1; j <= m; j++) {
                table[i][j] = s.charAt(j - 1) - '0';
            }
        }

        final Helper helper = new Helper(n, m, table);
        final int[] counts = new int[n + 1];
        Arrays.fill(counts, INITIAL_VALUE);
        counts[1] = 0;
        counts[n] = 0;
        for (int i = n - 1; i > 2; i--) {
            counts[i] = helper.calcShortestPathFromBackward(i, i + 1, i + m, counts);
        }

        final StringJoiner joiner = new StringJoiner(" ");
        for (int index = 2; index < n; index++) {
            final int i = index;
            final int min = IntStream.range(1, m)
                .filter(j -> i - j >= 1 && counts[i - j] != INITIAL_VALUE)
                .map(j -> counts[i - j] + helper.calcShortestPathFromBackward(i - j, i + 1, i + m, counts))
                .min()
                .orElse(INITIAL_VALUE);

            if (min < INITIAL_VALUE) {
                joiner.add(Integer.toString(min));
            } else {
                joiner.add("-1");
            }

            for (int j = 1; j <= m && index - j >= 1; j++) {
                if (table[index - j][j] == 1 && counts[index - j] != INITIAL_VALUE) {
                    counts[index] = Math.min(counts[index], counts[index - j] + 1);
                }
            }
            counts[index + 1] = INITIAL_VALUE;
        }
        System.out.println(joiner);
    }

    private static class Helper {
        final int n;
        final int m;
        final int[][] table;

        Helper(final int n, final int m, final int[][] table) {
            this.n = n;
            this.m = m;
            this.table = table;
        }

        int calcShortestPathFromBackward(final int base, final int beginInclusive, final int endInclusive, final int[] counts) {
            if (!isInRangeOfN(base)) {
                return INITIAL_VALUE;
            }

            return IntStream.iterate(beginInclusive, i -> i <= endInclusive && isInRangeOfN(i), i -> i + 1)
                .filter(i -> isTeleportable(base, i, counts))
                .map(i -> counts[i] + 1)
                .min()
                .orElse(INITIAL_VALUE);
        }

        private boolean isInRangeOfN(final int num) {
            return 1 <= num && num <= n;
        }

        private boolean isTeleportable(final int from, final int to, final int[] contents) {
            return to > from &&
                to - from <= m &&
                table[from][to - from] == 1 &&
                contents[to] < INITIAL_VALUE;
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
