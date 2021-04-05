package AtCoder.other.ACL1.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Union-Findするときは、そのグループの代表的な値だけ見れば良い
今回はyが最小の座標
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair> list = IntStream.range(0, n)
            .mapToObj(i -> new Pair(i, scanner.nextInt(), scanner.nextInt()))
            .sorted(Comparator.comparingInt(pair -> pair.x))
            .collect(Collectors.toList());
        final UnionFindTree tree = new UnionFindTree(n);
        final Deque<Pair> queue = new ArrayDeque<>();
        for (final Pair pair : list) {
            if (queue.isEmpty() || queue.peekFirst().y > pair.y) {
                queue.addFirst(pair);
                continue;
            }
            for (final Pair tmp : queue) {
                if (tmp.y > pair.y) {
                    break;
                }
                tree.unit(pair.index, tmp.index);
            }
        }
        final Map<Integer, List<Integer>> map = IntStream.range(0, n)
            .mapToObj(tree::getRoot)
            .collect(Collectors.groupingBy(Function.identity()));
        final String answer = IntStream.range(0, n)
            .map(i -> map.get(tree.getRoot(i)).size())
            .mapToObj(Integer::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
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

    private static class Pair {
        final int index;
        final int x;
        final int y;

        Pair(final int index, final int x, final int y) {
            this.index = index;
            this.x = x;
            this.y = y;
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
