package AtCoder.ABC.ABC267.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();

        final Long[][] dp = new Long[n][m + 1];
        dp[0][1] = (long) array[0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= Math.min(m, i + 1); j++) {
                dp[i][j] = maxNullable(dp[i - 1][j], addNullable(dp[i - 1][j - 1], (long) j * array[i]));
            }
        }

        System.out.println(dp[n - 1][m]);
    }

    private static Long addNullable(final Long lhs, final Long rhs) {
        return operateNullable(lhs, rhs, Math::addExact);
    }

    private static Long maxNullable(final Long lhs, final Long rhs) {
        return operateNullable(lhs, rhs, Math::max);
    }

    private static <T> T operateNullable(final T lhs, final T rhs, final BinaryOperator<T> operator) {
        if (lhs != null && rhs != null) return operator.apply(lhs, rhs);
        if (lhs != null) return lhs;
        return rhs;
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
