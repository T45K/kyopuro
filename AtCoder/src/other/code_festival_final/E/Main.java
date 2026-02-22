package other.code_festival_final.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final int[][] dp = new int[n][2]; // 0 -> top, 1 -> bottom
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < n; i++) {
            final int current = list.get(i);
            for (int j = i + 1; j < n; j++) {
                final int next = list.get(j);
                if (next > current) {
                    dp[j][0] = Math.max(dp[j][0], dp[i][1] + 1);
                }
                if (next < current) {
                    dp[j][1] = Math.max(dp[j][1], dp[i][0] + 1);
                }
            }
        }
        final int answer = Arrays.stream(dp)
            .flatMapToInt(Arrays::stream)
            .max()
            .orElseThrow();
        System.out.println(answer >= 3 ? answer : 0);
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
