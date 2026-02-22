package ABC.ABC214.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
解説AC
部分文字列の種類数を求める問題は典型を知ることが大事
 */
public class Main {
  private static final long MOD = 1_000_000_007;

  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final String s = scanner.next();
    final long[] dp = new long[s.length() + 2];
    dp[0] = 1;
    for (int i = 0; i < s.length(); i++) {
      for (int j = i - 1; ; j--) {
        dp[i + 2] += dp[j + 1];
        dp[i + 2] %= MOD;
        if (j == -1 || s.charAt(j) == s.charAt(i)) {
          break;
        }
      }
    }

    final long answer = Arrays.stream(dp, 2, s.length() + 2)
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
  }
}
