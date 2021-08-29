package AtCoder.ABC.ABC216.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final String s = scanner.next();
    final String[] array = s.split("\\.");
    final int x = Integer.parseInt(array[0]);
    final int y = Integer.parseInt(array[1]);
    if (y <= 2) {
      System.out.println(x + "-");
    } else if (y <= 6) {
      System.out.println(x);
    } else {
      System.out.println(x + "+");
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
  }
}
