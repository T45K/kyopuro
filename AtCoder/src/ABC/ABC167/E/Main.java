package ABC.ABC167.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
組み合わせ 問題文勘違い
全て隣り合わない組み合わせは m*(m-1)^(n-1)
1組隣り合う場合，隣り同士は同じ色なので ↑/(m-1)
↑に組み合わせをかける．一つ右とペアになると考えれば n-1C1 通り
2組以降も同じように考える
 */
public class Main {
    private static final int MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long m = scanner.nextInt();
        final long k = scanner.nextInt();

        if (m == 1) {
            if (k == n - 1) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            return;
        }

        final CombinationCalculator calculator = new CombinationCalculator(n + 1, MOD);

        long answer = 0;
        long base = m;
        for (int j = 1; j < n; j++) {
            base *= m - 1;
            base %= MOD;
        }

        final long inv = modInv(m - 1);
        for (int i = 0; i <= k; i++) {
            answer += base * calculator.calc(n - 1, i);
            answer %= MOD;

            base *= inv;
            base %= MOD;
        }

        System.out.println(answer);
    }

    private static long modInv(long a) {
        long res = 1;
        long n = MOD - 2;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return res;
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

        /**
         * mod 込みで nCk を計算した結果を返す．
         *
         * @param n 組み合わせの対象となる数
         * @param k 組み合わせる個数
         * @return 組み合わせの結果
         */
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
