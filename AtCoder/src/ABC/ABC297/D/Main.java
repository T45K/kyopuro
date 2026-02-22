package ABC.ABC297.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();

        final long answer = recursive(Math.min(a, b), Math.max(a, b));
        System.out.println(answer);
    }

    private static long recursive(final long smaller, final long bigger) {
        final long mod = bigger % smaller;
        if (mod == 0) {
            return bigger / smaller - 1;
        }
        return bigger / smaller + recursive(mod, smaller);
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
