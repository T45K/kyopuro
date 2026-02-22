package ABC.ABC216.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 各筒の先頭から見ていく
 今見ている筒の先頭のボールAがすでに出現している場合，Aが先頭である二つの筒の先頭を取り除き，再びその筒の先頭を見る
 */
public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    scanner.nextInt();
    final int m = scanner.nextInt();
    final List<Deque<Integer>> list = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      final int k = scanner.nextInt();
      final Deque<Integer> queue = new ArrayDeque<>();
      for (int j = 0; j < k; j++) {
        queue.addLast(scanner.nextInt());
      }
      list.add(queue);
    }

    final Deque<Integer> operations = IntStream.range(0, m)
        .boxed()
        .collect(Collectors.toCollection(ArrayDeque::new));

    final Map<Integer, Integer> tops = new HashMap<>(); // value, index
    while (!operations.isEmpty()) {
      final int operationTargetIndex = operations.pollFirst();
      if (list.get(operationTargetIndex).isEmpty()) {
        continue;
      }
      final int currentTopValue = Optional.ofNullable(list.get(operationTargetIndex).peekFirst()).orElseThrow();
      if (tops.containsKey(currentTopValue)) {
        list.get(operationTargetIndex).removeFirst();
        operations.add(operationTargetIndex);

        list.get(tops.get(currentTopValue)).removeFirst();
        operations.add(tops.get(currentTopValue));
        tops.remove(currentTopValue);
      } else {
        tops.put(currentTopValue, currentTopValue);
      }
    }

    final boolean isEmpty = list.stream()
        .allMatch(Collection::isEmpty);
    if (isEmpty) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
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
