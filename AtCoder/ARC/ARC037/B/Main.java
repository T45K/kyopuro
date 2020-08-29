package AtCoder.ARC.ARC037.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final UnionFindTree unionFind = new UnionFindTree(n);
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            unionFind.unit(u, v);
            graph.computeIfAbsent(u, value -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, value -> new ArrayList<>()).add(u);
        }

        final long answer = IntStream.rangeClosed(1, n)
            .boxed()
            .collect(Collectors.groupingBy(unionFind::getRoot))
            .values().stream()
            .filter(list -> list.stream()
                .mapToInt(node -> graph.get(node) == null ? 0 : graph.get(node).size())
                .sum() / 2 == list.size() - 1)
            .count();
        System.out.println(answer);
    }

    private static class UnionFindTree {
        private final Integer[] nodes;
        private final List<Integer> indices = new ArrayList<>();

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        private Integer[] init(final int numOfNodes) {
            return IntStream.rangeClosed(0, numOfNodes)
                .boxed()
                .toArray(Integer[]::new);
        }

        /**
         * 引数のノードが属している木の根を返す．
         *
         * @param nodeNumber ノードの番号
         * @return 根，つまり属している集合の中の一番小さい値
         */
        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final Integer index : indices) {
                    nodes[index] = rootNode;
                }
                indices.clear();
                return nodeNumber;
            }

            indices.add(nodeNumber);
            return getRoot(rootNode);
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

            if (rootA == rootB) {
                return;
            }

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
    }
}
    