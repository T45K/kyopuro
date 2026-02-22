package ABC.ABC282.D;

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        final List<Pair<Integer, Integer>> edges = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .collect(Collectors.toList());

        final MultiValueMap<Integer, Integer> graph = makeGraph(edges);
        final Collection<List<Integer>> connectedGraphNodes = groupingToConnectedNodes(n, edges);

        try {
            final long answer = connectedGraphNodes.stream()
                .mapToLong(nodes -> {
                    final Map<Integer, Color> nodeColorMap = classify(graph, nodes);
                    final Map<Color, Long> colorCounts = nodeColorMap.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));
                    final long countInConnectedGraphNodes = nodes.stream()
                        .mapToLong(node -> {
                            final Color color = nodeColorMap.get(node);
                            final long contraryColorCount = colorCounts.getOrDefault(color.contrary(), 0L);
                            return contraryColorCount - graph.get(node).size();
                        }).sum();
                    final long countInUnconnectedGraphNodes = (long) nodes.size() * (n - nodes.size());
                    return countInConnectedGraphNodes + countInUnconnectedGraphNodes;
                }).sum();
            System.out.println(answer / 2);
        } catch (NotBipartiteGraphException e) {
            System.out.println(0);
        }
    }

    private static Map<Integer, Color> classify(final MultiValueMap<Integer, Integer> graph, final List<Integer> nodes) {
        final int head = nodes.get(0);
        final Deque<Integer> nodeQueue = new ArrayDeque<>();
        final Map<Integer, Color> colorDecidedNodes = new LinkedHashMap<>();
        colorDecidedNodes.put(head, Color.BLACK);
        nodeQueue.addLast(head);

        while (!nodeQueue.isEmpty()) {
            final int currentNode = nodeQueue.poll();
            final Color nextColor = colorDecidedNodes.get(currentNode).contrary();
            for (final int nextNode : graph.get(currentNode)) {
                if (!colorDecidedNodes.containsKey(nextNode)) {
                    colorDecidedNodes.put(nextNode, nextColor);
                    nodeQueue.addLast(nextNode);
                } else if (colorDecidedNodes.get(nextNode) != nextColor) {
                    throw new NotBipartiteGraphException();
                }
            }
        }

        return colorDecidedNodes;
    }

    private static MultiValueMap<Integer, Integer> makeGraph(final List<Pair<Integer, Integer>> edges) {
        final MultiValueMap<Integer, Integer> graph = new MultiValueMap<>();
        for (final Pair<Integer, Integer> edge : edges) {
            graph.add(edge.first, edge.second);
            graph.add(edge.second, edge.first);
        }
        return graph;
    }

    private static Collection<List<Integer>> groupingToConnectedNodes(final int n, final List<Pair<Integer, Integer>> edges) {
        final UnionFindTree unionFind = new UnionFindTree(n + 1);
        for (final Pair<Integer, Integer> edge : edges) {
            unionFind.unit(edge.first, edge.second);
        }
        return IntStream.rangeClosed(1, n)
            .boxed()
            .collect(Collectors.groupingBy(unionFind::getRoot))
            .values();
    }

    private enum Color {
        BLACK, WHITE;

        public int index() {
            return this.ordinal();
        }

        public Color contrary() {
            return Color.values()[1 - this.index()];
        }
    }

    private static class NotBipartiteGraphException extends RuntimeException {
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

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
