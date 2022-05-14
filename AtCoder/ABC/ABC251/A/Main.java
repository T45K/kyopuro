package AtCoder.ABC.ABC251.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    private static final int LENGTH = 6;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        Stream.generate(() -> s)
            .limit((s.length() + LENGTH - 1) / LENGTH)
            .reduce((lhs, rhs) -> lhs + rhs)
            .map(concatenated -> concatenated.substring(0, LENGTH))
            .ifPresent(System.out::println);
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
