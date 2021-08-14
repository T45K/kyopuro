package AtCoder.ABC.ABC214.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int s = scanner.nextInt();
    final int t = scanner.nextInt();
    int count = 0;
    for (int i = 0; i <= s; i++) {
      for (int j = 0; j <= s; j++) {
        for (int k = 0; k <= s; k++) {
          if (i + j + k <= s && i * j * k <= t) {
            count++;
          }
        }
      }
    }
    System.out.println(count);
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
