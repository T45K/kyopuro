package AtCoder.ABC.ABC214.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    if (n >= 1 && n <= 125) {
      System.out.println(4);
    } else if (n <= 211) {
      System.out.println(6);
    } else {
      System.out.println(8);
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
