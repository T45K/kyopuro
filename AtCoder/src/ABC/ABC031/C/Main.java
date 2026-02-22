package ABC.ABC031.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        final int answer = IntStream.range(0, n)
            .map(t -> IntStream.range(0, n)
                .filter(a -> a != t)
                .mapToObj(a -> countAokiScore(array, t, a))
                .max(Comparator.comparingInt(score -> score.value))
                .map(score -> score.index)
                .map(a -> countTakahashiScore(array, t, a))
                .orElseThrow()
            ).max()
            .orElseThrow();
        System.out.println(answer);
    }

    private static Score countAokiScore(final int[] array, final int t, final int a) {
        final int begin = Math.min(t, a);
        final int end = Math.max(t, a);
        final int value = IntStream.rangeClosed(begin, end)
            .filter(i -> (i - begin) % 2 == 1)
            .map(i -> array[i])
            .sum();
        return new Score(a, value);
    }

    private static int countTakahashiScore(final int[] array, final int t, final int a) {
        final int begin = Math.min(t, a);
        final int end = Math.max(t, a);
        return IntStream.rangeClosed(begin, end)
            .filter(i -> (i - begin) % 2 == 0)
            .map(i -> array[i])
            .sum();
    }

    private static class Score {
        final int index;
        final int value;

        Score(final int index, final int value) {
            this.index = index;
            this.value = value;
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
