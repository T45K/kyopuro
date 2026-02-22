package ABC.ABC207.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
解説AC
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = Stream.generate(scanner::nextLong)
            .limit(n)
            .mapToLong(Long::longValue)
            .toArray();
        final long[] accum = new long[n];
        accum[0] = array[0];
        for (int i = 1; i < n; i++) {
            accum[i] = accum[i - 1] + array[i];
        }

        final long[][] table = new long[n][n + 1];
        final long[][] memo = new long[n + 1][n + 1];
        for (int index = 0; index < n; index++) {
            table[index][1] = 1;
            for (int numGroups = 2; numGroups <= index + 1; numGroups++) {
                table[index][numGroups] = memo[numGroups - 1][(int) (accum[index] % numGroups)];
            }
            for (int numGroups = 1; numGroups <= index + 1; numGroups++) {
                memo[numGroups][(int) (accum[index] % (numGroups + 1))] += table[index][numGroups];
                memo[numGroups][(int) (accum[index] % (numGroups + 1))] %= MOD;
            }
        }

        final long answer = Arrays.stream(table[n - 1])
            .reduce((a, b) -> (a + b) % MOD)
            .orElseThrow();
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
