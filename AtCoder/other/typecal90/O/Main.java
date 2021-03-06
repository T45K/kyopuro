package AtCoder.other.typecal90.O;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
解説AC
選べない玉を引いておくという考え方
N個のボールから差がk以上となるようにa個のボールを選ぶ方法は N-(k-1)(a-1)Ca 通り
これは，あるx個の玉からa個選んだ時，選んだ玉の間（(a-1)箇所）に(k-1)個の玉を入れれば差がk以上という条件を満たすことから，
x+(k-1)(a-1) = N と考えられる
 */
public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final CombinationCalculator calculator = new CombinationCalculator(n + 1, MOD);
        final String answer = IntStream.rangeClosed(1, n)
            .mapToObj(i -> // 差がi以上になるように選ぶ
                IntStream.rangeClosed(1, (n + i - 1) / i)
                    .mapToObj(j ->  // N個中j個選ぶ
                        calculator.calc(n - (i - 1) * (j - 1), j))
                    .reduce(0L, (a, b) -> (a + b) % MOD)
                    .toString())
            .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(answer);
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
