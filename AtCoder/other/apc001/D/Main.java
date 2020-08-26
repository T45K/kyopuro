package AtCoder.other.apc001.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final BiFunction<Integer, Integer, Boolean> isTree = (nodes, edges) -> edges == nodes - 1;
        if (isTree.apply(n, m)) {
            System.out.println(0);
            return;
        }

        final BiFunction<Integer, Integer, Boolean> isImpossible = (nodes, edges) -> nodes < 2 * (nodes - edges - 1);
        if (isImpossible.apply(n, m)) {
            System.out.println("Impossible");
            return;
        }

        final List<Long> costs = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .collect(Collectors.toList());

        final List<Edge> edges = IntStream.range(0, m)
            .mapToObj(i -> new Edge(scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());

        final List<List<Long>> forest = makeForest(edges, n).stream()
            .map(list -> list.stream()
                .map(costs::get)
                .sorted()
                .collect(Collectors.toList()))
            .collect(Collectors.toList());

        final long answer = forest.stream()
            .mapToLong(list -> list.get(0))
            .sum()
            + forest.stream()
            .flatMap(list -> list.subList(1, list.size()).stream())
            .sorted()
            .limit(2 * (n - m - 1) - forest.size())
            .mapToLong(Long::longValue)
            .sum();
        System.out.println(answer);
    }

    private static List<List<Integer>> makeForest(final List<Edge> edges, final int n) {
        final UnionFindTree tree = new UnionFindTree(n);
        for (final Edge edge : edges) {
            tree.unit(edge.nodeA, edge.nodeB);
        }

        return new ArrayList<>(IntStream.range(0, n)
            .boxed()
            .collect(Collectors.groupingBy(tree::getRoot))
            .values());
    }

    private static class Edge {
        final int nodeA;
        final int nodeB;

        Edge(final int nodeA, final int nodeB) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }
    }

    private static class UnionFindTree {
        private final Integer[] nodes;
        private final List<Integer> indices = new ArrayList<>();

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        private Integer[] init(final int numOfNodes) {
            return IntStream.range(0, numOfNodes)
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

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
