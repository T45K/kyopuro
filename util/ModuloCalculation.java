package util;

/**
 * 組み合わせ計算を集めたライブラリ
 */
class ModuloCalculation {
    /**
     * 組み合わせの計算をDPを用いて高速化する．
     * <p>
     * {@link CombinationCalculator} の方が計算量，メモリ容量的に使いやすい．
     *
     * @param size 　二次元配列のサイズ
     *             　配列の大きさから size C x はできないことに注意
     * @return 二次元配列
     */
    private static long[][] createCombinationTable(final int size) {
        final int mod = 1000000007;
        final long[][] table = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    table[i][j] = 1;
                    continue;
                }

                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % mod;
            }
        }

        return table;
    }

    /**
     * 素数modを法としたaの逆元を計算する．
     *
     * @param a   逆元を計算したい値
     * @param mod 法となる素数
     * @return modを法としたaの逆元
     */
    private static long modInv(long a, final long mod) {
        long res = 1;
        long n = mod - 2;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }

    /**
     * 高速にmod込みで組み合わせを計算できる．<br>
     *
     * @see <a href="http://drken1215.hatenablog.com/entry/2018/06/08/210000">けんちょんの競プロ精進記録</a>
     */
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
}
