package ABC.ABC305.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final String[] table = Stream.generate(scanner::next)
            .limit(h)
            .toArray(String[]::new);

        final Pair<Integer, Integer> answer = new Solver(h, w, table).solve();
        System.out.println((answer.first + 1) + " " + (answer.second + 1));
    }

    private static class Solver {
        private final int h;
        private final int w;
        private final String[] table;

        Solver(final int h, final int w, final String[] table) {
            this.h = h;
            this.w = w;
            this.table = table;
        }

        public Pair<Integer, Integer> solve() {
            return IntStream.range(0, h)
                .boxed()
                .flatMap(i -> IntStream.range(0, w)
                    .filter(j ->
                        // @formatter:off
                        match(i, j, "..." +
                                    "..#" +
                                    ".##")
                            ||
                        match(i, j, ".##" +
                                    "..#" +
                                    ".##")
                            ||
                        match(i, j, ".##" +
                                    "..#" +
                                    "...")
                            ||
                        match(i, j, "..." +
                                    "#.." +
                                    "##.")
                            ||
                        match(i, j, "##." +
                                    "#.." +
                                    "##.")
                            ||
                        match(i, j, "##." +
                                    "#.." +
                                    "...")
                            ||
                        match(i, j, "..." +
                                    "#.#" +
                                    "###")
                            ||
                        match(i, j, "###" +
                                    "#.#" +
                                    "...")
                            ||
                        match(i, j, "###" +
                                    "#.#" +
                                    "###")
                    )
                        // @formatter:on
                    .mapToObj(j -> new Pair<>(i, j))
                )
                .findFirst()
                .orElseThrow();
        }

        private boolean match(final int x, final int y, final String targetShape) {
            return IntStream.range(0, 3)
                .allMatch(i -> IntStream.range(0, 3)
                    .allMatch(j -> {
                        final int xDiff = i - 1;
                        final int yDiff = j - 1;
                        final char targetChar = targetShape.charAt(i * 3 + j);
                        if (targetChar == '.') {
                            return !isCookie(x + xDiff, y + yDiff);
                        } else {
                            return isCookie(x + xDiff, y + yDiff);
                        }
                    })
                );
        }

        private boolean isCookie(final int x, final int y) {
            if (x < 0 || x >= h || y < 0 || y >= w) {
                return false;
            }
            return table[x].charAt(y) == '#';
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

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
