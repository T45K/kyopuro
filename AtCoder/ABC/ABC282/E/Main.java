package AtCoder.ABC.ABC282.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Long> list = Stream.generate(scanner::nextLong)
            .limit(n)
            .collect(Collectors.toList());
        final BiFunction<Long, Long, Long> calc = (Long a, Long b) -> (iterativePow(a, b, m) + iterativePow(b, a, m)) % m;
        final List<Edge> edges = IntStream.range(0, n)
            .boxed()
            .flatMap(i -> IntStream.range(i + 1, n)
                .mapToObj(j -> new Edge(i, j, calc.apply(list.get(i), list.get(j)))))
            .sorted(Comparator.comparing(edge -> edge.cost, Comparator.reverseOrder()))
            .collect(Collectors.toList());
        final UnionFindTree unionFind = new UnionFindTree(n + 1);
        long sum = 0;
        for (final Edge edge : edges) {
            if (!unionFind.isSame(edge.a, edge.b)) {
                sum += edge.cost;
                unionFind.unit(edge.a, edge.b);
            }
        }
        System.out.println(sum);
    }

    private static long iterativePow(final long a, final long b, final long mod) {
        return iterativePow(a, b, 1, mod);
    }

    private static long iterativePow(final long a, final long b, final long result, final long mod) {
        if (b == 0) {
            return result;
        } else if ((b & 1) == 1) {
            return iterativePow(a * a % mod, b >> 1, result * a % mod, mod);
        } else {
            return iterativePow(a * a % mod, b >> 1, result, mod);
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

    private static class Edge {
        final int a;
        final int b;
        final long cost;

        Edge(final int a, final int b, final long cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
