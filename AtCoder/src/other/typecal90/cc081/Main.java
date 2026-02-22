package other.typecal90.cc081;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  private static final int MAX = 5_000;

  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final int k = scanner.nextInt();
    final int[][] table = new int[MAX + 1][MAX + 1];
    for (int i = 0; i < n; i++) {
      final int a = scanner.nextInt();
      final int b = scanner.nextInt();
      final int aRange = a + k + 1;
      final int bRange = b + k + 1;
      table[a][b]++;
      if (aRange <= MAX) {
        table[aRange][b]--;
      }
      if (bRange <= MAX) {
        table[a][bRange]--;
      }
      if (aRange <= MAX && bRange <= MAX) {
        table[aRange][bRange]++;
      }
    }
    for (int i = 0; i <= MAX; i++) {
      for (int j = 0; j < MAX; j++) {
        table[i][j + 1] += table[i][j];
      }
    }
    for (int i = 0; i < MAX; i++) {
      for (int j = 0; j <= MAX; j++) {
        table[i + 1][j] += table[i][j];
      }
    }

    final int answer = Arrays.stream(table)
        .flatMapToInt(Arrays::stream)
        .max()
        .orElse(0);
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
