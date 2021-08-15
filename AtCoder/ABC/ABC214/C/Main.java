package AtCoder.ABC.ABC214.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
2週する
 */
public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final long[] sArray = Stream.generate(scanner::nextInt)
        .limit(n)
        .mapToLong(Integer::longValue)
        .toArray();
    final long[] tArray = Stream.generate(scanner::nextInt)
        .limit(n)
        .mapToLong(Integer::longValue)
        .toArray();
    for (int i = 0; i < n * 2; i++) {
      final int next = (i + 1) % n;
      tArray[next] = Math.min(tArray[next], tArray[i % n] + sArray[i % n]);
    }
    final String answer = Arrays.stream(tArray)
        .mapToObj(Objects::toString)
        .collect(Collectors.joining("\n"));
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
