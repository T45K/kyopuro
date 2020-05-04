package AtCoder.ABC.ABC166.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] heights = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            heights[i] = scanner.nextInt();
        }

        final boolean[] isHighest = new boolean[n + 1];
        Arrays.fill(isHighest, true);
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            if (heights[a] >= heights[b]) {
                isHighest[b] = false;
            } else if (heights[a] <= heights[b]) {
                isHighest[a] = false;
            }
        }

        final long answer = IntStream.rangeClosed(1, n)
                .mapToObj(i -> isHighest[i])
                .filter(Boolean::booleanValue)
                .count();
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
    