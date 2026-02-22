package ABC.ABC305.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        scanner.nextInt();

        final boolean[] isVisited = new boolean[n + 1];
        int current = 1;
        final Deque<Integer> route = new ArrayDeque<>();
        while (true) {
            isVisited[current] = true;
            route.addLast(current);
            if (current == n) {
                scanner.next();
                return;
            }

            final int k = scanner.nextInt();
            final List<Integer> list = Stream.generate(scanner::nextInt)
                .limit(k)
                .collect(Collectors.toList());

            final Optional<Integer> next = list.stream().filter(v -> !isVisited[v]).findFirst();
            if (next.isPresent()) {
                current = next.get();
            } else {
                route.pollLast();
                current = Objects.requireNonNull(route.pollLast());
            }
            System.out.println(current);
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
