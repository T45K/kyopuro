package ARC.ARC019.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
回文 やるだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String a = scanner.next();

        final long count = IntStream.range(0, a.length() / 2)
                .filter(i -> a.charAt(i) != a.charAt(a.length() - i - 1))
                .count();

        if (count == 0) {
            System.out.println(25 * (a.length() / 2 * 2));
        } else if (count == 1) {
            System.out.println(25 * (a.length() - 2) + 48);
        } else {
            System.out.println(25 * a.length());
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
    }
}
