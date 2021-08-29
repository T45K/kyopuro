package AtCoder.ABC.ABC216.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    long n = scanner.nextLong();
    System.out.println(recursive(n));
  }

  private static StringBuilder recursive(final long n) {
    if (n == 1) {
      return new StringBuilder("A");
    }
    return recursive(n / 2).append(n % 2 == 0 ? "B" : "BA");
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

    long nextLong() {
      return Long.parseLong(next());
    }
  }
}
