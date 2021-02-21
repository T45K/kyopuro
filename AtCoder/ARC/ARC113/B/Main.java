package AtCoder.ARC.ARC113.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final int modA = a % 10;
        if (modA == 0 || modA == 1) {
            System.out.println(modA);
            return;
        }
        int count = 0;
        int itr = modA;
        do {
            count++;
            itr *= modA;
            itr %= 10;
        } while (itr != modA);

        long pow = iterativePow(b, c, count);
        if (pow == 0) {
            pow += count;
        }

        final long answer = iterativePow(modA, pow, 10);
        System.out.println(answer);
    }

    private static long iterativePow(long a, long b, long mod) {
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
    }
}
