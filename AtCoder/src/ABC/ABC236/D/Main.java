package ABC.ABC236.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static int[][] scores;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        scores = new int[2 * n - 1][2 * n];
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < 2 * n - i - 1; j++) {
                scores[i][i + 1 + j] = scanner.nextInt();
            }
        }

        final boolean[] used = new boolean[2 * n];
        final int answer = recursive(n, used, new ArrayDeque<>());
        System.out.println(answer);
    }

    private static int recursive(final int n, final boolean[] used, final Deque<Integer> values) {
        if (values.size() == n) {
            return values.stream().reduce(0, (a, b) -> a ^ b);
        }

        int max = 0;
        final int first = IntStream.range(0, 2 * n).filter(i -> !used[i]).findFirst().orElseThrow();
        used[first] = true;
        for (int i = 0; i < 2 * n; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            values.addLast(scores[first][i]);
            max = Math.max(max, recursive(n, used, values));
            values.removeLast();
            used[i] = false;
        }
        used[first] = false;
        return max;
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
