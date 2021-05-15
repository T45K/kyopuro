package AtCoder.ABC.ABC201.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final Map<Character, List<Integer>> map = IntStream.range(0, s.length())
            .boxed()
            .collect(Collectors.groupingBy(s::charAt));
        final Set<Integer> correct = new HashSet<>(get(map, 'o'));
        final Set<Integer> suspicious = new HashSet<>(get(map, '?'));
        final Function<Integer, Boolean> contains = value -> correct.contains(value) || suspicious.contains(value);

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
                return contains.apply(first) &&
                    contains.apply(second) &&
                    contains.apply(third) &&
                    contains.apply(fourth);
            }).count();
        System.out.println(answer);
    }

    private static List<Integer> get(final Map<Character, List<Integer>> map, final char c) {
        return Optional.ofNullable(map.get(c)).orElse(Collections.emptyList());
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
