package AtCoder.ARC.ARC111.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
解説AC
(10^N)/M%Mと((10^N)%M)/M%Mの結果が変わらないことを利用する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final int m = scanner.nextInt();
        final long value = iterativePow(10, n, m * m);
        System.out.println(value / m % m);
    }

    private static long iterativePow(long a, long b, final int mod) {
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
                tmp %= mod;
            }
            a *= a;
            a %= mod;
            b >>= 1;
        }

        return tmp;
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
