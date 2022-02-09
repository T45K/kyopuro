package AtCoder.ABC.ABC234.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
解説AC
DP[i][j] = i番目のアルファベット(1:a, 2:b, 3:c, ...)でj文字目まで作るときのパターン
状態遷移は
DP[i][j] = Σ
1. DP[i-1][j]、つまり、アルファベットiを使わずにi-1文字目まででj文字作る
2. DP[i-1][j-1]*j、つまり、1文字だけアルファベットiを入れてj文字作る。入れ方はj通り
3. DP[i-1][j-2]*jC2、つまり、2文字アルファベットiを入れてj文字作る。入れ方はjC2通り
4. 以下、アルファベットiが出現する回数回繰り返す
 */
public class Main {
    private static final long MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final Map<Character, Long> counts = s.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final CombinationCalculator combinationCalculator = new CombinationCalculator(s.length() + 1, (int) MOD);
        final long[][] dp = new long[27][s.length() + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= 26; i++) {
            final int count = counts.getOrDefault((char) ('a' + i - 1), 0L).intValue();
            for (int j = 0; j <= s.length(); j++) {
                for (int k = 0; k <= Math.min(count, j); k++) {
                    dp[i][j] += dp[i - 1][j - k] * combinationCalculator.calc(j, k);
                    dp[i][j] %= MOD;
                }
            }
        }
        final long answer = Arrays.stream(dp[26], 1, s.length() + 1)
            .reduce((a, b) -> (a + b) % MOD)
            .orElseThrow();
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
    }
}
