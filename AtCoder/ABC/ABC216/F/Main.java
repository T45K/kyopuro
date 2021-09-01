package AtCoder.ABC.ABC216.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Bの部分集合の計算で，和はmax(A)以下の場合だけを考えればよい
 */
public class Main {
    private static final int MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] aArray = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final int[] bArray = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final List<Pair> list = IntStream.range(0, n)
            .mapToObj(i -> new Pair(aArray[i], bArray[i]))
            .sorted(Comparator.comparingInt(pair -> pair.a))
            .collect(Collectors.toList());
        final int[][] dp = new int[n][5_001];
        int sum = 0;
        if (list.get(0).a >= list.get(0).b) {
            sum++;
        }
        dp[0][list.get(0).b]++;
        for (int i = 1; i < list.size(); i++) {
            final Pair pair = list.get(i);
            dp[i][pair.b]++;
            for (int j = pair.b; j <= 5_000; j++) {
                dp[i][j] += dp[i - 1][j - pair.b];
                dp[i][j] %= MOD;
            }
            for (int j = 0; j <= pair.a; j++) {
                sum += dp[i][j];
                sum %= MOD;
            }
            for (int j = 0; j <= 5_000; j++) {
                dp[i][j] += dp[i - 1][j];
                dp[i][j] %= MOD;
            }
        }
        System.out.println(sum);
    }

    private static class Pair {
        final int a;
        final int b;

        Pair(final int a, final int b) {
            this.a = a;
            this.b = b;
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
