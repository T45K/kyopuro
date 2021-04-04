package AtCoder.AGC.AGC052.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*
解説AC
一辺がd-1の正六角形を作ってから十二角形を作るパターンに注意
 */
public class Main {
    private static final int MOD = 998_244_353;
    private static final long inv2 = BigInteger.TWO.modInverse(BigInteger.valueOf(MOD)).longValue();

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int d = scanner.nextInt();
        final CombinationCalculator calculator = new CombinationCalculator(2 * d + 1, MOD);
        final long answer = calculator.calc(2 * d, d) * inv2 % MOD;
        System.out.println(answer);
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
