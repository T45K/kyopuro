package ABC.ABC214.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
コストの小さい辺の順に連結していく
 */
public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final List<Edge> list = Stream.generate(() -> new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
        .limit(n - 1)
        .sorted(Comparator.comparingLong(Edge::getWeight))
        .collect(Collectors.toList());

    final UnionFindTree unionFind = new UnionFindTree(n);
    final long[] elements = new long[n + 1];
    Arrays.fill(elements, 1);
    long sum = 0;
    for (final Edge edge : list) {
      final int fromRoot = unionFind.getRoot(edge.from);
      final int toRoot = unionFind.getRoot(edge.to);
      unionFind.unit(edge.from, edge.to);
      sum += elements[fromRoot] * elements[toRoot] * edge.weight;
      elements[unionFind.getRoot(edge.from)] = elements[fromRoot] + elements[toRoot];
    }
    System.out.println(sum);
  }

  private static class Edge {
    final int from;
    final int to;
    final long weight;

    Edge(final int from, final int to, final long weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    final long getWeight() {
      return this.weight;
    }
  }

  private static class UnionFindTree {
    private final int[] nodes;
    private final Deque<Integer> indices = new ArrayDeque<>();

    UnionFindTree(final int numOfNodes) {
      this.nodes = IntStream.rangeClosed(0, numOfNodes).toArray();
    }

    /**
     * 引数のノードが属している木の根を返す．
     *
     * @param nodeNumber ノードの番号
     * @return 根，つまり属している集合の中の一番小さい値
     */
    int getRoot(final int nodeNumber) {
      final int rootNode = nodes[nodeNumber];
      if (rootNode != nodeNumber) {
        indices.add(nodeNumber);
        return getRoot(rootNode);
      }

      final Consumer<Integer> updateRoot = index -> nodes[index] = rootNode;
      indices.forEach(updateRoot);
      indices.clear();
      return nodeNumber;
    }

    /**
     * 二つのノードが同じ集合に属しているかを判定する．
     *
     * @param nodeA ノード
     * @param nodeB ノード
     * @return 二つのノードが同じ集合に属しているかの判定結果
     */
    boolean isSame(final int nodeA, final int nodeB) {
      return getRoot(nodeA) == getRoot(nodeB);
    }

    /**
     * 引数のノードが属する集合を合体させる．
     *
     * @param nodeA ノード
     * @param nodeB ノード
     */
    void unit(final int nodeA, final int nodeB) {
      final int rootA = getRoot(nodeA);
      final int rootB = getRoot(nodeB);
      nodes[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
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
