package other.hhkb2020.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
あるマスを照らしたい時、そのマスの縦横の直線上にランプを置けば良い。
ランプをN箇所に置ける時、そのマスを照らせるランプの置き方は
（N箇所のどこかに必ずランプを置く置き方全通り）*（N箇所以外のランプの置き方全通り）になるので
これを足し合わせる。
 */
public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final boolean[][] table = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = s.charAt(j) == '.';
            }
        }
        final int all = IntStream.range(0, h)
            .map(i -> (int) IntStream.range(0, w)
                .filter(j -> table[i][j])
                .count())
            .sum();
        final int[][] counts = new int[h][w];
        for (int i = 0; i < h; i++) {
            int counter = 0;
            int start = Integer.MAX_VALUE;
            for (int j = 0; j < w; j++) {
                if (table[i][j]) {
                    start = Math.min(start, j);
                    counter++;
                } else {
                    if (start == Integer.MAX_VALUE) {
                        continue;
                    }
                    for (int k = start; k < j; k++) {
                        counts[i][k] = counter;
                    }
                    counter = 0;
                    start = Integer.MAX_VALUE;
                }
            }
            if (start < Integer.MAX_VALUE) {
                for (int k = start; k < w; k++) {
                    counts[i][k] = counter;
                }
            }
        }
        for (int j = 0; j < w; j++) {
            int counter = 0;
            int start = Integer.MAX_VALUE;
            for (int i = 0; i < h; i++) {
                if (table[i][j]) {
                    start = Math.min(start, i);
                    counter++;
                } else {
                    if (start == Integer.MAX_VALUE) {
                        continue;
                    }
                    for (int k = start; k < i; k++) {
                        counts[k][j] += counts[k][j] > 0 ? counter - 1 : counter;
                    }
                    counter = 0;
                    start = Integer.MAX_VALUE;
                }
            }
            if (start < Integer.MAX_VALUE) {
                for (int k = start; k < h; k++) {
                    counts[k][j] += counts[k][j] > 0 ? counter - 1 : counter;
                }
            }
        }
        final long answer = IntStream.range(0, h)
            .flatMap(i -> Arrays.stream(counts[i]))
            .filter(value -> value > 0)
            .mapToLong(value -> (iterativePow(value) - 1) * iterativePow((all - value) % MOD))
            .reduce(0, (a, b) -> (a + b) % MOD);
        System.out.println(answer);
    }

    private static long iterativePow(long b) {
        long a = 2;
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
                tmp %= MOD;
            }
            a *= a;
            a %= MOD;
            b >>= 1;
        }

        return tmp;
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
    