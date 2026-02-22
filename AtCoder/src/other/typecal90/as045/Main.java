package other.typecal90.as045;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
計算量が 3^15 * 15 > 10^8 なのでJavaだと間に合わない
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final List<Point> list = Stream.generate(() -> new Point(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());
        final int[][] disassemble = disassemble(n);
        final long[] distances = calcDistances(list, disassemble);

        final long[][] dp = new long[k + 1][1 << n];
        System.arraycopy(distances, 1, dp[1], 1, (1 << n) - 1);
        for (int i = 2; i <= k; i++) {
            final int immutableI = i;
            for (int j = 1; j < 1 << n; j++) {
                final int immutableJ = j;
                dp[i][j] = recursive(j, 0, disassemble[j], 0, value -> Math.max(dp[immutableI - 1][value], distances[immutableJ - value]));
            }
        }
        System.out.println(dp[k][(1 << n) - 1]);
    }

    private static long recursive(final int base, final int tmp, final int[] disassemble, final int index, final Function<Integer, Long> calc) {
        if (index == disassemble.length) {
            return calc.apply(tmp);
        }

        if (index == disassemble.length - 1) {
            if (tmp == 0) {
                return recursive(base, tmp + (1 << disassemble[index]), disassemble, index + 1, calc);
            }
            if (tmp + (1 << disassemble[index]) == base) {
                return recursive(base, tmp, disassemble, index + 1, calc);
            }
        }

        return Math.min(
            recursive(base, tmp, disassemble, index + 1, calc),
            recursive(base, tmp + (1 << disassemble[index]), disassemble, index + 1, calc)
        );
    }

    private static int[][] disassemble(final int n) {
        final int[][] table = new int[1 << n][];
        for (int tmp = 1; tmp < 1 << n; tmp++) {
            final int bit = tmp;
            table[tmp] = IntStream.range(0, n)
                .filter(i -> (bit & (1 << i)) > 0)
                .toArray();
        }
        return table;
    }

    private static long[] calcDistances(final List<Point> list, final int[][] disassemble) {
        final int n = list.size();
        final long[] distances = new long[1 << n];
        distances[0] = Long.MAX_VALUE / 2;
        for (int bit = 1; bit < 1 << n; bit++) {
            final int[] array = disassemble[bit];
            for (int i = 0; i < array.length - 1; i++) {
                final Point base = list.get(array[i]);
                for (int j = i + 1; j < array.length; j++) {
                    distances[bit] = Math.max(distances[bit], base.distance(list.get(array[j])));
                }
            }
        }
        return distances;
    }

    private static class Point {
        final long x;
        final long y;

        Point(final long x, final long y) {
            this.x = x;
            this.y = y;
        }

        long distance(final Point point) {
            return (this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y);
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
