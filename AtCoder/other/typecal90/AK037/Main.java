package AtCoder.other.typecal90.AK037;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Stream;

/*
dp[i][j]の最大値は max(dp[i-1][j-cooking.r],...,dp[i-1][j-cooking.l]) + cooking.v なので尺取する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();
        final Cooking[] cookingArray = Stream.generate(() -> new Cooking(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .toArray(Cooking[]::new);
        final long[][] dp = new long[n][w + 1];
        for (final long[] array : dp) {
            Arrays.fill(array, -1);
        }

        dp[0][0] = 0;
        for (int j = cookingArray[0].l; j <= cookingArray[0].r && j <= w; j++) {
            dp[0][j] = cookingArray[0].v;
        }

        for (int i = 1; i < n; i++) {
            System.arraycopy(dp[i - 1], 0, dp[i], 0, w + 1);

            final Cooking cooking = cookingArray[i];
            final TreeMap<Long, Integer> treeMap = new TreeMap<>(Long::compareTo);
            for (int j = w - cooking.r; j <= w - cooking.l; j++) {
                if (dp[i - 1][j] == -1) {
                    continue;
                }
                treeMap.compute(dp[i - 1][j], (k, v) -> v == null ? 1 : v + 1);
            }

            for (int j = w; j - cooking.l >= 0; j--) {
                if (!treeMap.isEmpty()) {
                    final Map.Entry<Long, Integer> maxKey = treeMap.lastEntry();
                    dp[i][j] = Math.max(dp[i][j], maxKey.getKey() + cooking.v);
                    final long removeTarget = dp[i - 1][j - cooking.l];
                    if (removeTarget >= 0) {
                        final int num = treeMap.get(removeTarget);
                        if (num == 1) {
                            treeMap.remove(removeTarget);
                        } else {
                            treeMap.put(removeTarget, num - 1);
                        }
                    }
                }

                final int addTargetIndex = j - cooking.r - 1;
                if (addTargetIndex >= 0 && dp[i - 1][addTargetIndex] >= 0) {
                    treeMap.compute(dp[i - 1][addTargetIndex], (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }
        System.out.println(dp[n - 1][w]);
    }

    private static class Cooking {
        final int l;
        final int r;
        final long v;

        Cooking(final int l, final int r, final long v) {
            this.l = l;
            this.r = r;
            this.v = v;
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
