package ARC.ARC034.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
数学
約数列挙
 */
public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        if (a == b) {
            System.out.println(1);
            return;
        }

        IntStream.rangeClosed(b + 1, a)
            .mapToObj(Main::primeFactorization)
            .reduce(new HashMap<>(), Main::mergeMap)
            .values().stream()
            .mapToLong(i -> i + 1)
            .reduce((x, y) -> (x * y) % MOD)
            .ifPresent(System.out::println);
    }

    private static Map<Integer, Long> mergeMap(final Map<Integer, Long> a, final Map<Integer, Long> b) {
        for (final Map.Entry<Integer, Long> entry : b.entrySet()) {
            a.compute(entry.getKey(), (k, v) -> v == null ? entry.getValue() : v + entry.getValue());
        }
        return a;
    }

    private static Map<Integer, Long> primeFactorization(int n) {
        final double sqrt = Math.sqrt(n);
        final Map<Integer, Long> countMap = new HashMap<>();
        for (int i = 2; i <= sqrt; i++) {
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
