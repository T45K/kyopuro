package ABC.ABC227.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long answer = Stream.generate(scanner::nextInt)
            .limit(n)
            .filter(Predicate.not(Main::isCorrectPrediction))
            .count();
        System.out.println(answer);
    }

    private static boolean isCorrectPrediction(final int s) {
        return IntStream.rangeClosed(1, 1000)
            .anyMatch(a -> IntStream.rangeClosed(1, 1000)
                .anyMatch(b -> 4 * a * b + 3 * a + 3 * b == s));
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
