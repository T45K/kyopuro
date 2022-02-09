package AtCoder.ABC.ABC238.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int q = scanner.nextInt();

        final List<Node> list = Stream.concat(
                Stream.generate(() -> new Node(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
                    .limit(m),
                IntStream.range(0, q)
                    .mapToObj(i -> new QueryNode(i, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            ).sorted(Comparator.comparingInt(node -> node.w))
            .collect(Collectors.toList());

        final UnionFindTree unionFind = new UnionFindTree(n);
        final boolean[] answers = new boolean[q];

        for (final Node node : list) {
            if (node instanceof QueryNode) {
                if (!unionFind.isSame(node.u, node.v)) {
                    answers[((QueryNode) node).id] = true;
                }
                continue;
            }
            unionFind.unit(node.u, node.v);
        }

        final String answer = IntStream.range(0, q)
            .mapToObj(i -> answers[i] ? "Yes" : "No")
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

    private static class Node {
        final int u;
        final int v;
        final int w;

        Node(final int u, final int v, final int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static class QueryNode extends Node {
        final int id;

        QueryNode(final int id, final int u, final int v, final int w) {
            super(u, v, w);
            this.id = id;
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
