package ABC.ABC239.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
最初に繋がっている集合を求める
余っている接続可能な辺の数が大きい順に結合していく
 */
public class Main {

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] limits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            limits[i] = scanner.nextInt();
        }

        final int sum = Arrays.stream(limits, 1, n + 1).sum();
        if (sum != (n - 1) * 2) {
            System.out.println(-1);
            return;
        }

        final List<Pair<Integer, Integer>> edges = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .collect(Collectors.toList());

        System.out.println(solver(n, limits, edges).orElse("-1"));
    }

    private static Optional<String> solver(final int n, final int[] limits, final List<Pair<Integer, Integer>> initialEdges) {
        final int[] connectedCounts = countConnection(n, initialEdges);
        final boolean isLimitOver = IntStream.rangeClosed(1, n).filter(i -> connectedCounts[i] > limits[i]).count() > 0;
        if (isLimitOver) {
            return Optional.empty();
        }

        final List<List<Integer>> groups = groupConnectedNodes(n, initialEdges).stream()
            .sorted(Comparator.comparing(
                it -> it.stream()
                    .mapToInt(i -> limits[i] - connectedCounts[i])
                    .sum(),
                Comparator.reverseOrder()))
            .collect(Collectors.toList());

        final Deque<Integer> nodePool = groups.get(0).stream()
            .filter(it -> connectedCounts[it] < limits[it])
            .collect(Collectors.toCollection(ArrayDeque::new));
        final List<String> answers = new ArrayList<>();
        for (int i = 1; i < groups.size(); i++) {
            if (nodePool.isEmpty()) {
                return Optional.empty();
            }

            final Optional<Integer> connectableNode = groups.get(i)
                .stream()
                .filter(it -> limits[it] > connectedCounts[it])
                .findAny();
            if (connectableNode.isEmpty()) {
                return Optional.empty();
            }

            final int newlyConnectedNode = connectableNode.get();
            final int pooledNode = Objects.requireNonNull(nodePool.peekFirst());
            connectedCounts[newlyConnectedNode]++;
            connectedCounts[pooledNode]++;
            answers.add(newlyConnectedNode + " " + pooledNode);

            if (limits[pooledNode] - connectedCounts[pooledNode] == 0) {
                nodePool.pollFirst();
            }
            nodePool.addAll(groups.get(i).stream()
                .filter(it -> connectedCounts[it] < limits[it])
                .collect(Collectors.toList()));
        }

        if (nodePool.isEmpty()) {
            return Optional.of(String.join("\n", answers));
        } else {
            return Optional.empty();
        }
    }

    private static int[] countConnection(final int n, final List<Pair<Integer, Integer>> edges) {
        final int[] connectedCounts = new int[n + 1];
        for (final Pair<Integer, Integer> edge : edges) {
            connectedCounts[edge.first]++;
            connectedCounts[edge.second]++;
        }
        return connectedCounts;
    }

    private static Collection<List<Integer>> groupConnectedNodes(final int n, final List<Pair<Integer, Integer>> edges) {
        final UnionFindTree unionFind = new UnionFindTree(n);
        for (final Pair<Integer, Integer> edge : edges) {
            unionFind.unit(edge.first, edge.second);
        }
        return IntStream.rangeClosed(1, n)
            .boxed()
            .collect(Collectors.groupingBy(unionFind::getRoot))
            .values();
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
