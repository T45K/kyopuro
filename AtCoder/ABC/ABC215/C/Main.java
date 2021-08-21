package AtCoder.ABC.ABC215.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final String s = scanner.next();
    final int k = scanner.nextInt();
    final ArrayDeque<Integer> queue = IntStream.range(0, s.length())
        .boxed()
        .collect(Collectors.toCollection(ArrayDeque::new));
    final List<String> list = new ArrayList<>();
    permutation(s, queue, list, new char[s.length()], 0);

    final List<String> answers = list.stream()
        .distinct()
        .sorted()
        .collect(Collectors.toList());

    System.out.println(answers.get(k - 1));
  }

  private static void permutation(final String s, final Deque<Integer> queue, final List<String> list, final char[] target, final int index) {
    if (index == s.length()) {
      list.add(new String(target));
    }

    final int size = queue.size();
    for (int i = 0; i < size; i++) {
      final int tmp = Optional.ofNullable(queue.pollFirst()).orElseThrow();
      target[index] = s.charAt(tmp);
      permutation(s, queue, list, target, index + 1);
      queue.addLast(tmp);
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
