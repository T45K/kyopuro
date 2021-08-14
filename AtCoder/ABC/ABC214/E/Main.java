package AtCoder.ABC.ABC214.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO solve
public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final Supplier<String> solve = () -> {
      final int n = scanner.nextInt();
      final List<Pair> list = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextInt()))
          .limit(n)
          .sorted(Comparator.comparingInt(Pair::getL).thenComparing(Pair::getR))
          .collect(Collectors.toList());
      int min = 0;
      for (final Pair pair : list) {
        if (pair.getR() <= min) {
          return "No";
        }
        min = Math.max(min + 1, pair.getL());
      }
      return "Yes";
    };
    final int t = scanner.nextInt();
    final String answer = Stream.generate(solve)
        .limit(t)
        .collect(Collectors.joining("\n"));
    System.out.println(answer);
  }

  private static class Pair {
    private final int l;
    private final int r;

    Pair(final int l, final int r) {
      this.l = l;
      this.r = r;
    }

    public int getL() {
      return l;
    }

    public int getR() {
      return r;
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

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      if (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          return reader.readLine();
        } catch (final IOException e) {
          throw new RuntimeException(e);
        }
      }

      return tokenizer.nextToken("\n");
    }
  }
}
