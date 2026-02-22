package ABC.ABC238.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        long tmp = 1;
        long sum = 0;
        while (tmp <= n) {
            final long min = Math.min(tmp * 10 - 1, n) % MOD;
            final long modTmp = tmp % MOD;
            sum += min * (min + 1) / 2;
            sum %= MOD;
            sum += MOD - modTmp * modSub(modTmp, 1) / 2 % MOD;
            sum %= MOD;
            sum += MOD - modSub(modTmp, 1) * (modSub(min, modTmp) + 1) % MOD;
            sum %= MOD;
            tmp *= 10;
        }
        System.out.println(sum);
    }

    private static long modSub(final long a, final long b) {
        return (MOD + a - b) % MOD;
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
