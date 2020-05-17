package AtCoder.ABC.ABC168.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

// TODO solve
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Sardine, Long> plusSardines = new HashMap<>();
        final Map<Sardine, Long> minusSardines = new HashMap<>();

        for (int i = 0; i < n; i++) {
            final long a = scanner.nextLong();
            final long b = scanner.nextLong();

            final long gcd = euclideanAlgorithm(abs(a), abs(b));
            if (a / abs(a) == b / abs(b)) {
                plusSardines.compute(new Sardine(abs(a) / gcd, abs(b) / gcd), (k, v) -> v == null ? 1 : v + 1);
            } else {
                minusSardines.compute(new Sardine(abs(b) / gcd, abs(a) / gcd), (k, v) -> v == null ? 1 : v + 1);
            }
        }

        final long plusSize = plusSardines.size();
        final long minusSize = minusSardines.size();
        long notSelected = 0;

        long all = 1;
        long sub = 0;
        for (int i = 1; i <= n; i++) {
            all *= 2;
            all %= MOD;
            if (i == n - 2) {
                sub = all;
            }
        }
        all--;

        for (final Map.Entry<Sardine, Long> entry : plusSardines.entrySet()) {
            if (minusSardines.containsKey(entry.getKey())) {
                notSelected += entry.getValue() * minusSardines.get(entry.getKey());
                notSelected %= MOD;
            }
        }
        System.out.println((all - notSelected * sub + MOD) % MOD);
    }

    private static long euclideanAlgorithm(final long a, final long b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
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

    private static class Sardine {
        final long a;
        final long b;

        Sardine(final long a, final long b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return (int) (a + b);
        }

        @Override
        public boolean equals(final Object obj) {
            if (!(obj instanceof Sardine)) {
                return false;
            }
            final Sardine sardine = (Sardine) obj;
            return this.a == sardine.a && this.b == sardine.b;
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
