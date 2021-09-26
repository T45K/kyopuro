package AtCoder.ABC.ABC222.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int d = scanner.nextInt();

        if (d == 1) {
            System.out.println(((iterativePow(2, n - 1) - 1) * 3 + 2) % MOD);
            return;
        }

        long numOfNodesInDepth = 1;
        long sum = 0;
        long additional = 0;
        for (int i = 1; i <= n; i++) {
            if (n - i >= d) { // 下だけで計上できる
                sum += numOfNodesInDepth * iterativePow(2, d) % MOD;
            }

            if (i > 1 && i - 1 < d) { // i-1個上を見れる，折り返し付き
                additional += iterativePow(2, d - i - 2);
                additional %= MOD;
            }

            if (n - i + 2 < d) { // 1個上を見たときに折り返せない
                additional = additional + MOD - iterativePow(2, d - 2);
                additional %= MOD;
            }

            if (i - 1 == d) {
                additional++;
                additional %= MOD;
            }

            sum += additional * numOfNodesInDepth % MOD;
            sum %= MOD;
            numOfNodesInDepth *= 2;
            numOfNodesInDepth %= MOD;
        }
        System.out.println(sum);
    }

    private static long iterativePow(final long a, final long b) {
        return iterativePow(a, b, 1);
    }

    private static long iterativePow(final long a, final long b, final long result) {
        if (b <= 0) {
            return result;
        } else if ((b & 1) == 1) {
            return iterativePow(a * a % MOD, b >> 1, result * a % MOD);
        } else {
            return iterativePow(a * a % MOD, b >> 1, result % MOD);
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
