package AtCoder.other.typecal90.bp068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 事前に差分情報を計算しておく
public class Main {
  private static final String AMBIGUOUS = "Ambiguous";

  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final int q = scanner.nextInt();
    final List<Query> queries = Stream.generate(() -> new Query(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
        .limit(q)
        .collect(Collectors.toList());

    final long[] sums = new long[n]; // sum[i] = Ai + Ai+1
    for (final Query query : queries) {
      if (query.type == 0) {
        sums[query.x] = query.v;
      }
    }

    final long[] accum = new long[n]; // accum[i] = sums[1] - sums[2] + sums[3] - ...
    for (int i = 1; i < n; i++) {
      accum[i] = accum[i - 1] + (i % 2 == 1 ? sums[i] : -sums[i]);
    }

    final UnionFindTree tree = new UnionFindTree(n + 1);
    final StringJoiner joiner = new StringJoiner(System.lineSeparator());
    for (final Query query : queries) {
      if (query.type == 0) {
        tree.unit(query.x, query.y);
        continue;
      }

      if (!tree.isSame(query.x, query.y)) {
        joiner.add(AMBIGUOUS);
        continue;
      }

      final long diff = query.y % 2 == 0 ? (accum[query.y - 1] - accum[query.x - 1]) : (accum[query.x - 1] - accum[query.y - 1]);
      final long base = (query.x - query.y) % 2 == 0 ? query.v : -query.v;
      joiner.add(Long.toString(diff + base));
    }

    System.out.println(joiner);
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

  private static class Query {
    final int type;
    final int x;
    final int y;
    final long v;

    Query(final int type, final int x, final int y, final int v) {
      this.type = type;
      this.x = x;
      this.y = y;
      this.v = v;
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
