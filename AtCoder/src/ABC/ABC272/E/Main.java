package ABC.ABC272.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        final Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            final int step = i + 1;
            final int value = list.get(i);
            if (value >= n) {
                continue;
            }
            final int beginCount = (value + step) >= 0 ? 1 : ((-value + step - 1) / step);
            for (int j = beginCount; j <= m && value + j * step < n; j++) {
                map.computeIfAbsent(j, k -> new HashSet<>()).add(value + j * step);
            }
        }

        final String answer = IntStream.rangeClosed(1, m)
            .map(i -> {
                final Set<Integer> appeared = map.get(i);
                if (appeared == null) {
                    return 0;
                }
                return IntStream.rangeClosed(0, n)
                    .filter(((IntPredicate) appeared::contains).negate())
                    .findFirst()
                    .orElseThrow();
            })
            .mapToObj(Integer::toString)
            .collect(Collectors.joining("\n"));
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
