package ABC.ABC251.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        IntStream.rangeClosed(1, n)
            .mapToObj(i -> new Submission(i, scanner.next(), scanner.nextInt()))
            .collect(Collectors.toMap(Submission::getValue, Function.identity(), prioritizeFirst()))
            .values().stream()
            .max(Comparator.comparing(Submission::getScore)
                .thenComparing(Submission::getIndex, Comparator.reverseOrder()))
            .map(Submission::getIndex)
            .ifPresent(System.out::println);
    }

    private static <T> BinaryOperator<T> prioritizeFirst() {
        return (lhs, rhs) -> lhs;
    }

    private static class Submission {
        final int index;
        final String value;
        final int score;

        Submission(final int index, final String value, final int score) {
            this.index = index;
            this.value = value;
            this.score = score;
        }

        int getIndex() {
            return index;
        }

        String getValue() {
            return value;
        }

        int getScore() {
            return score;
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
