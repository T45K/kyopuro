package ABC.ABC305.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int p = scanner.next().toCharArray()[0];
        final int q = scanner.next().toCharArray()[0];
        final int min = Math.min(p, q);
        final int max = Math.max(p, q);
        final int answer = IntStream.range(min, max)
            .map(i -> {
                switch (i) {
                    case 'A':
                        return 3;
                    case 'B':
                        return 1;
                    case 'C':
                        return 4;
                    case 'D':
                        return 1;
                    case 'E':
                        return 5;
                    case 'F':
                        return 9;
                    default:
                        throw new IllegalStateException();
                }
            }).sum();
        System.out.println(answer);
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
