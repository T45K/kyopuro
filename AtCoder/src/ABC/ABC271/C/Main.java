package ABC.ABC271.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final int DUMMY = 1_000_000_001;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, Long> counts = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final ArrayDeque<Integer> queue = counts.entrySet().stream()
            .flatMap(entry -> Stream.concat(
                Stream.of(entry.getKey()),
                Stream.generate(() -> DUMMY).limit(entry.getValue() - 1)
            ))
            .sorted()
            .collect(Collectors.toCollection(ArrayDeque::new));
        int currentNumber = 0;
        for (int i = 1; !queue.isEmpty(); i++) {
            final int head = queue.peekFirst();
            if (head == i) {
                currentNumber = i;
                queue.pollFirst();
                continue;
            }
            if (queue.size() >= 2) {
                currentNumber = i;
                queue.pollLast();
                queue.pollLast();
                continue;
            }
            break;
        }
        System.out.println(currentNumber);
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
