package AtCoder.ABC.ABC300.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final char[][] table = new char[h][w];
        for (int i = 0; i < h; i++) {
            table[i] = scanner.next().toCharArray();
        }
        final List<Pair<Integer, Integer>> centers = IntStream.range(1, h - 1)
            .boxed()
            .flatMap(i -> IntStream.range(1, w - 1)
                .filter(j -> isCenter(table, i, j))
                .mapToObj(j -> new Pair<>(i, j))
            ).collect(Collectors.toList());

        final int[] answers = new int[Math.min(h, w) + 1];
        for (final Pair<Integer, Integer> center : centers) {
            final int level = measureLevel(table, center.first, center.second);
            answers[level]++;
        }

        final String answer = Arrays.stream(answers, 1, answers.length)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" "));
        System.out.println(answer);
    }

    private static boolean isCenter(final char[][] table, final int x, final int y) {
        return table[x - 1][y - 1] == '#' && table[x - 1][y] == '.' && table[x - 1][y + 1] == '#'
            && table[x][y - 1] == '.' && table[x][y] == '#' && table[x][y + 1] == '.'
            && table[x + 1][y - 1] == '#' && table[x + 1][y] == '.' && table[x + 1][y + 1] == '#';
    }

    private static int measureLevel(final char[][] table, final int x, final int y) {
        for (int i = 1; ; i++) {
            try {
                if (table[x - i][y - i] == '.' || table[x - i][y + i] == '.' || table[x + i][y - i] == '.' || table[x + i][y + i] == '.') {
                    return i - 1;
                }
            } catch (final ArrayIndexOutOfBoundsException e) {
                return i - 1;
            }
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
