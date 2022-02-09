package AtCoder.ABC.ABC238.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final UnionFindTree unionFind = new UnionFindTree(n);
        for (int i = 0; i < q; i++) {
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            unionFind.unit(l - 1, r);
        }

        if (unionFind.isSame(0, n)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
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
    }
}
