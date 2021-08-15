package AtCoder.ABC.ABC214.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
解説AC
Lの小さい順に候補のボールを考えて，Rの小さい順に箱に突っ込んでいく
 */
public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final Supplier<String> solve = () -> {
      final int n = scanner.nextInt();
      final PriorityQueue<Pair> lSortedQueue = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextInt()))
          .limit(n)
          .sorted(Comparator.comparingInt(Pair::getL).thenComparing(Pair::getR))
          .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparingInt(Pair::getL))));
      int index = 0;
      final PriorityQueue<Integer> rSortedQueue = new PriorityQueue<>();
      for (int i = 0; i < n; i++) {
        if (rSortedQueue.isEmpty()) {
          final Pair poll = lSortedQueue.poll();
          index = Optional.ofNullable(poll).orElseThrow().getL();
          rSortedQueue.add(poll.getR());
        }
        while (!lSortedQueue.isEmpty() && lSortedQueue.peek().getL() == index) {
          final Pair poll = lSortedQueue.poll();
          rSortedQueue.add(poll.getR());
        }
        final int r = Optional.ofNullable(rSortedQueue.poll()).orElseThrow();
        if (r < index) {
          return "No";
        }
        index++;
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
  }
}
