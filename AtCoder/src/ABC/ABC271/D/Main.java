package ABC.ABC271.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int s = scanner.nextInt();
        final List<Pair<Integer, Integer>> list = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());

        final char[][] dp = new char[n][s + 1];
        final BiConsumer<Integer, Integer> updateHIfPossible = (i, j) -> {
            if (j <= s) {
                dp[i][j] = 'H';
            }
        };
        final BiConsumer<Integer, Integer> updateTIfPossible = (i, j) -> {
            if (j <= s) {
                dp[i][j] = 'S';
            }
        };
        updateHIfPossible.accept(0, list.get(0).first);
        updateTIfPossible.accept(0, list.get(0).second);
        for (int i = 1; i < n; i++) {
            final Pair<Integer, Integer> pair = list.get(i);
            for (int j = 0; j < s; j++) {
                if (dp[i - 1][j] != 0) {
                    updateHIfPossible.accept(i, j + pair.first);
                    updateTIfPossible.accept(i, j + pair.second);
                }
            }
        }

        if (dp[n - 1][s] == 0) {
            System.out.println("No");
            return;
        }

        final char[] answers = new char[n];
        int index = s;
        for (int i = n - 1; i >= 0; i--) {
            answers[i] = dp[i][index];
            index -= dp[i][index] == 'H' ? list.get(i).first : list.get(i).second;
        }

        final String answer = IntStream.range(0, n)
            .mapToObj(i -> answers[i])
            .map(Objects::toString)
            .collect(Collectors.joining());

        System.out.println("Yes");
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
