package AtCoder.ABC.ABC292.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final MultiValueMap<Integer, Integer> graph = new MultiValueMap<>();
        final UnionFindTree unionFind = new UnionFindTree(n);
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.add(u, v);
            graph.add(v, u);
            unionFind.unit(u, v);
        }

        final Collection<List<Integer>> connectedGraphs = IntStream.rangeClosed(1, n)
            .boxed()
            .collect(Collectors.groupingBy(unionFind::getRoot))
            .values();
        final boolean answer = connectedGraphs.stream()
            .allMatch(connectedGraph -> connectedGraph.stream()
                .mapToInt(node -> graph.get(node).size())
                .sum() / 2 == connectedGraph.size());
        System.out.println(answer ? "Yes" : "No");
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

    private static class MultiValueMap<K, V> extends HashMap<K, List<V>> {

        public void add(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        @Override
        public List<V> get(final Object key) {
            return super.getOrDefault(key, Collections.emptyList());
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
