package AtCoder.ABC.ABC198.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long r = scanner.nextInt();
        final long x = scanner.nextInt();
        final long y = scanner.nextInt();
        final long answer = (long) Math.sqrt((x * x + y * y) / (r * r));
        if (answer == 0) { // ここに注意
            System.out.println(2);
        } else if (answer * answer * r * r == x * x + y * y) {
            System.out.println(answer);
        } else {
            System.out.println(answer + 1);
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
    