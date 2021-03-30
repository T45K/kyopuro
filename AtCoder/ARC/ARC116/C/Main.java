package AtCoder.ARC.ARC116.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
解説AC
https://atcoder.jp/contests/arc116/editorial/1041

まず，1->2->2 のような列を 1*2*1 のような数字を掛け合わせ列と捉える
dp[i][j] = 数字iをj個の(1以外の)数字を掛け合わせて表現した時のパターンの個数
とする
dp[i][j]にnCjを掛けることで，1を突っ込んだ元の列を再現できる
 */
public class Main {
    private static final int MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int length = (int) Math.min(Math.sqrt(m), n);
        final long[][] dp = new long[m + 1][length + 2];
        final CombinationCalculator calculator = new CombinationCalculator(n + 1, MOD);
        long sum = 1;
        for (int i = 2; i <= m; i++) {
            dp[i][1] = 1;
            for (int j = 1; j <= length && dp[i][j] > 0; j++) {
                for (int k = 2; i * k <= m; k++) {
                    dp[i * k][j + 1] += dp[i][j];
                    dp[i * k][j + 1] %= MOD;
                }
                sum += dp[i][j] * calculator.calc(n, j) % MOD;
                sum %= MOD;
            }
        }
        System.out.println(sum);
    }


    private static class CombinationCalculator {
        private final int size;
        private final int mod;
        private final long[] factorials;
        private final long[] invertedElements;
        private final long[] invertedFactorials;

        CombinationCalculator(final int size, final int mod) {
            this.size = size;
            this.mod = mod;
            this.factorials = new long[size];
            this.invertedElements = new long[size];
            this.invertedFactorials = new long[size];
            init();
        }

        private void init() {
            factorials[0] = 1;
            factorials[1] = 1;
            invertedFactorials[0] = 1;
            invertedFactorials[1] = 1;
            invertedElements[1] = 1;
            for (int i = 2; i < size; i++) {
                factorials[i] = factorials[i - 1] * i % mod;
                invertedElements[i] = mod - invertedElements[mod % i] * (mod / i) % mod;
                invertedFactorials[i] = invertedFactorials[i - 1] * invertedElements[i] % mod;
            }
        }

        long calc(final int n, final int k) {
            return factorials[n] * (invertedFactorials[k] * invertedFactorials[n - k] % mod) % mod;
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
