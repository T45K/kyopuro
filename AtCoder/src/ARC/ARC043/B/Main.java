package ARC.ARC043.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
DP 4問選ぶところがヒント
 */
public class Main {
    private static final long MOD = 1_000_000_007;
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Integer> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .sorted()
            .collect(Collectors.toList());

        final long[][] dp = new long[4][n];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                int index = Collections.binarySearch(list, list.get(j) * 2, lowerBoundComparator);
                index = index > 0 ? index : ~index;
                if (index < list.size()) {
                    dp[i][index] += dp[i - 1][j];
                    dp[i][index] %= MOD;
                }
            }

            for (int j = 1; j < n; j++) {
                dp[i][j] += dp[i][j - 1];
                dp[i][j] %= MOD;
            }
        }

        Arrays.stream(dp[3]).reduce((a, b) -> (a + b) % MOD)
            .ifPresent(System.out::println);
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
    