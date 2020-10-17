package AtCoder.ABC.ABC180.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long x = scanner.nextLong();
        final long y = scanner.nextLong();
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();


        long tmp = x;
        long count = 0;
        while (tmp * a < b && tmp * a < y) {
            count++;
            tmp *= a;
        }

        if (tmp >= y) {
            System.out.println(count);
            return;
        }
        System.out.println((y - 1 - tmp) / b + count);
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
