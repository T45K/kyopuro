package AtCoder.ABC.ABC239.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        final int[] connectedCounts = new int[n + 1];
        final UnionFindTree unionFind = new UnionFindTree(n);
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            connectedCounts[a]++;
            connectedCounts[b]++;
            if (connectedCounts[a] > limits[a] || connectedCounts[b] > limits[b]) {
                System.out.println(-1);
                return;
            }
            unionFind.unit(a, b);
        }

        final Map<Integer, List<Integer>> roots = IntStream.rangeClosed(1, n)
            .boxed()
            .collect(Collectors.groupingBy(unionFind::getRoot));

        final Deque<Integer> nodePool = new ArrayDeque<>();
        for (final int node : roots.get(1)) {
            if (connectedCounts[node] == limits[node]) {
                continue;
            }
            nodePool.addLast(node);
        }
        if (nodePool.isEmpty()) {
            System.out.println(-1);
            return;
        }

        roots.remove(1);
        final List<Integer> rootKeys = roots.keySet().stream()
            .sorted(Comparator.comparing(
                it -> roots.get(it).stream()
                    .mapToInt(i -> limits[i] - connectedCounts[i])
                    .sum(),
                Comparator.reverseOrder())
            ).collect(Collectors.toList());
        final List<String> answers = new ArrayList<>();
        for (final int key : rootKeys) {
            if (nodePool.isEmpty()) {
                System.out.println(-1);
                return;
            }

            final Optional<Integer> connectableNode = roots.get(key)
                .stream()
                .filter(it -> limits[it] > connectedCounts[it])
                .findAny();
            if (connectableNode.isEmpty()) {
                System.out.println(-1);
                return;
            }

            final int newlyConnectedNode = connectableNode.get();
            final int pooledNode = Objects.requireNonNull(nodePool.peekFirst());
            connectedCounts[newlyConnectedNode]++;
            connectedCounts[pooledNode]++;
            answers.add(newlyConnectedNode + " " + pooledNode);
            if (limits[pooledNode] - connectedCounts[pooledNode] == 0) {
                nodePool.pollFirst();
            }
            for (final int node : roots.get(key)) {
                if (connectedCounts[node] < limits[node]) {
                    nodePool.addLast(node);
                }
            }
        }
        if (!nodePool.isEmpty()) {
            System.out.println(-1);
            return;
        }

        final String answer = String.join("\n", answers);
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
