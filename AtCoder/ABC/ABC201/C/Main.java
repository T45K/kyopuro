package AtCoder.ABC.ABC201.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final Map<Character, List<Integer>> map = IntStream.range(0, s.length())
            .boxed()
            .collect(Collectors.groupingBy(s::charAt));
        final List<Integer> correct = get(map, 'o');
        final List<Integer> suspicious = get(map, '?');
        final List<Integer> wrong = get(map, 'x');
        if (correct.size() > 4 || wrong.size() == 10) {
            System.out.println(0);
            return;
        }

        final long answer = IntStream.rangeClosed(0, 9999)
            .filter(i -> {
                int first = i / 1000;
                int second = i % 1000 / 100;
                final int third = i % 100 / 10;
                final int fourth = i % 10;
                final boolean allMatch = correct.stream()
                    .allMatch(j -> j == first || j == second || j == third || j == fourth);
                if (!allMatch) {
                    return false;
                }
                return contains(correct, suspicious, first) &&
                    contains(correct, suspicious, second) &&
                    contains(correct, suspicious, third) &&
                    contains(correct, suspicious, fourth);
            }).count();
        System.out.println(answer);
    }

    private static List<Integer> get(final Map<Character, List<Integer>> map, final char c) {
        return Optional.ofNullable(map.get(c)).orElse(Collections.emptyList());
    }

    private static boolean contains(final List<Integer> correct, final List<Integer> suspicious, final int value) {
        return correct.stream()
            .filter(v -> v == value)
            .count() +
            suspicious.stream()
                .filter(v -> v == value)
                .count() > 0;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
