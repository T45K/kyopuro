package ABC.ABC168.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

import static java.lang.Math.abs;

/*
数え上げ
仲の悪いイワシの選び方よりも，その数え方の方が難しい
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Sardine, Integer> plusSardines = new HashMap<>();
        final Map<Sardine, Integer> minusSardines = new HashMap<>();
        final BiFunction<Sardine, Integer, Integer> counter = (k, v) -> v == null ? 1 : v + 1;
        long zeroCount = 0;

        for (int i = 0; i < n; i++) {
            final long a = scanner.nextLong();
            final long b = scanner.nextLong();

            if (a == 0 && b == 0) {
                zeroCount++;
                continue;
            }

            if (a == 0) {
                plusSardines.compute(new Sardine(0, 1), counter);
                continue;
            }

            if (b == 0) {
                minusSardines.compute(new Sardine(0, 1), counter);
                continue;
            }

            final long gcd = euclideanAlgorithm(abs(a), abs(b));
            if (a / abs(a) == b / abs(b)) {
                plusSardines.compute(new Sardine(abs(a) / gcd, abs(b) / gcd), counter);
            } else {
                minusSardines.compute(new Sardine(abs(b) / gcd, abs(a) / gcd), counter);
            }
        }

        final long[] exp = new long[200_001];
        long base = 1;
        for (int i = 0; i <= 200_000; i++) {
            exp[i] = base;
            base *= 2;
            base %= MOD;
        }

        long answer = 1;

        for (final Map.Entry<Sardine, Integer> entry : plusSardines.entrySet()) {
            if (minusSardines.containsKey(entry.getKey())) {
                answer *= exp[entry.getValue()] + exp[minusSardines.get(entry.getKey())] - 1;
            } else {
                answer *= exp[entry.getValue()];
            }
            answer %= MOD;
        }

        for (final Map.Entry<Sardine, Integer> entry : minusSardines.entrySet()) {
            if (plusSardines.containsKey(entry.getKey())) {
                continue;
            }

            answer *= exp[entry.getValue()];
            answer %= MOD;
        }

        answer = (answer - 1 + zeroCount + MOD) % MOD;
        System.out.println(answer);
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
    }
}
