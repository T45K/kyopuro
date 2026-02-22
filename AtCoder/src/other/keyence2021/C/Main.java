package other.keyence2021.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*
(何も書かれてない箇所)^3 (Xとする) 通りの盤面が存在する
つまり，まずは何も書かれていない箇所に通らない経路の数*Xが考えられる
次に，emptyを通る箇所だがこれはそこにたどり着くまでの経路*3がR,D,Xに振られる
 */
public class Main {
    private static final long MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int k = scanner.nextInt();
        final String[][] table = new String[h][w];
        for (int i = 0; i < k; i++) {
            table[scanner.nextInt() - 1][scanner.nextInt() - 1] = scanner.next();
        }

        final long initial = iterativePow((long) h * w - k);
        final long divide = BigInteger.valueOf(3).modInverse(BigInteger.valueOf(MOD)).longValue();
        final long[][] routes = new long[h][w];
        routes[0][0] = initial;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (table[i][j] == null) {
                    final long plus = routes[i][j] * divide % MOD;
                    if (i < h - 1) {
                        routes[i + 1][j] += 2 * plus % MOD;
                        routes[i + 1][j] %= MOD;
                    }
                    if (j < w - 1) {
                        routes[i][j + 1] += 2 * plus % MOD;
                        routes[i][j + 1] %= MOD;
                    }
                    continue;
                }

                if (i < h - 1 && !table[i][j].equals("R")) {
                    routes[i + 1][j] += routes[i][j];
                    routes[i + 1][j] %= MOD;
                }
                if (j < w - 1 && !table[i][j].equals("D")) {
                    routes[i][j + 1] += routes[i][j];
                    routes[i][j + 1] %= MOD;
                }
            }
        }

        System.out.println(routes[h - 1][w - 1] % MOD);
    }

    private static long iterativePow(long b) {
        long base = 3;
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= base;
                tmp %= MOD;
            }
            base *= base;
            base %= MOD;
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
