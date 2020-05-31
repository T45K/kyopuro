package AtCoder.ABC.ABC169.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

/*
数学
まず素因数分解
そのあと，それぞれの素数の掛けられる回数Lに対して1+2+3+...+k<=Lを満たす最大のkを足し合わせる
(割る数は被りがないように選ばないといけないので，p^1，p^2，...，p^kを選択するため)
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final Map<Long, Long> map = primeFactorization(n);
        final long sum = map.values().stream()
            .mapToLong(value -> LongStream.iterate(1, l -> l * (l + 1) / 2 <= value, l -> l + 1)
                .reduce((l1, l2) -> l2)
                .orElseThrow())
            .sum();
        System.out.println(sum);
    }

    private static Map<Long, Long> primeFactorization(long n) {
        final double sqrt = Math.sqrt(n);
        final Map<Long, Long> countMap = new HashMap<>();
        for (long i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                countMap.compute(i, (k, v) -> v = v == null ? 1 : v + 1);
                n /= i;
                i--;
            }
        }

        if (n != 1) {
            countMap.compute(n, (k, v) -> v = v == null ? 1 : v + 1);
        }

        return countMap;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
