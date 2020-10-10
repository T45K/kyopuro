package AtCoder.other.hhkb2020.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        for (int unused = 0; unused < t; unused++) {
            final long n = scanner.nextInt();
            final long x = scanner.nextInt();
            final long y = scanner.nextInt();
            final long a = Math.max(x, y);
            final long b = Math.min(x, y);

            if (a + b > n) {
                System.out.println(0);
                continue;
            }

            final long tmp = n - a - b;
            final long sum = (tmp + 1) * tmp / 2 % MOD;
            final long all = ((tmp + 1) * tmp % MOD + tmp + 1 + MOD - sum) % MOD
                * (n - b + 1) % MOD
                * (n - a + 1) * 4 % MOD;

            final long sq = tmp * tmp % MOD;
            final long sq2 = (tmp + 1) * (tmp + 1) % MOD;
            final long overlapping = (sq * sq2 % MOD + MOD - tmp * 2 * sum % MOD + sq2) % MOD * 4 % MOD;

            System.out.println((MOD + all - overlapping) % MOD);
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
