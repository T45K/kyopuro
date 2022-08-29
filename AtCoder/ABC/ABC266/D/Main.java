package AtCoder.ABC.ABC266.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final int MAX_TIME = 100_000;
    private static final int HOLE_COUNT = 5;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, Snuke> map = Stream.generate(() -> new Snuke(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toMap(snuke -> snuke.t, Function.identity()));

        final long[][] dp = new long[MAX_TIME + 1][HOLE_COUNT];
        for (final long[] array : dp) {
            Arrays.fill(array, -1);
        }
        dp[0][0] = 0;

        for (int i = 0; i < MAX_TIME; i++) {
            for (int j = 0; j < HOLE_COUNT; j++) {
                if (j > 0) {
                    dp[i + 1][j - 1] = Math.max(dp[i][j], dp[i + 1][j - 1]);
                }
                dp[i + 1][j] = Math.max(dp[i][j], dp[i + 1][j]);
                if (j < HOLE_COUNT - 1) {
                    dp[i + 1][j + 1] = Math.max(dp[i][j], dp[i + 1][j + 1]);
                }
            }
            final Snuke snuke = map.get(i + 1);
            if (snuke != null && dp[i + 1][snuke.x] != -1) {
                dp[i + 1][snuke.x] += snuke.a;
            }
        }

        final long answer = Arrays.stream(dp[MAX_TIME]).max().orElseThrow();
        System.out.println(answer);
    }

    private static class Snuke {
        private final int t;
        private final int x;
        private final long a;

        Snuke(int t, int x, long a) {
            this.t = t;
            this.x = x;
            this.a = a;
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
