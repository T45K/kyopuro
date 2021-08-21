package AtCoder.ABC.ABC215.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
dp[今までどこを通ったか][今どこにいるか]
 */
public class Main {
  private static final long MOD = 998244353;

  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final String s = scanner.next();
    final int[] array = IntStream.range(0, n)
        .map(i -> s.charAt(i) - 'A')
        .toArray();
    final long[][] dp = new long[1 << 10][10];
    for (final int item : array) {
      final Map<Integer, Long> map = new HashMap<>();
      final int bit = 1 << item;
      for (int j = 0; j < 1 << 10; j++) {
        for (int k = 0; k < 10; k++) {
          final long tmp = dp[j][k];
          if ((j & bit) > 0 && k == item) {
            map.compute(j, (key, value) -> value == null ? tmp : (value + tmp));
          }
          if ((j & bit) == 0 && k != item) {
            map.compute(j | bit, (key, value) -> value == null ? tmp : (value + tmp));
          }
        }
      }
      for (final Map.Entry<Integer, Long> entry : map.entrySet()) {
        dp[entry.getKey()][item] += entry.getValue();
        dp[entry.getKey()][item] %= MOD;
      }
      dp[bit][item]++; // i から選び始める
      dp[bit][item] %= MOD;
    }

    final long answer = Arrays.stream(dp)
        .flatMapToLong(Arrays::stream)
        .reduce(0, (a, b) -> (a + b) % MOD);
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
