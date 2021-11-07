package AtCoder.ABC.ABC226.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
後ろの方から計算する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[][] requires = new int[n + 1][];
        final long[] costs = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            final int t = scanner.nextInt();
            costs[i] = t;
            final int k = scanner.nextInt();
            requires[i] = Stream.generate(scanner::nextInt)
                .limit(k)
                .mapToInt(Integer::intValue)
                .toArray();
        }
        final boolean[] acquired = new boolean[n + 1];
        final long answer = recursive(n, requires, costs, acquired);
        System.out.println(answer);
    }

    private static long recursive(final int current, final int[][] requires, final long[] costs, final boolean[] hasBeenAcquired) {
        if (current == 0 || hasBeenAcquired[current]) {
            return 0;
        }

        long sum = 0;
        for (int i = requires[current].length - 1; i >= 0; i--) {
            final int next = requires[current][i];
            sum += recursive(next, requires, costs, hasBeenAcquired);
        }
        hasBeenAcquired[current] = true;
        sum += costs[current];
        return sum;
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
