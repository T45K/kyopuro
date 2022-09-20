package AtCoder.ABC.ABC269.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Pair<Integer, Integer>, Integer> map = IntStream.range(0, n)
            .mapToObj(i -> new Pair<>(new Pair<>(scanner.nextInt(), scanner.nextInt()), i))
            .collect(Collectors.toMap(pair -> pair.first, pair -> pair.second));

        final UnionFindTree unionFind = new UnionFindTree(n - 1);
        for (final Map.Entry<Pair<Integer, Integer>, Integer> entry : map.entrySet()) {
            final Pair<Integer, Integer> point = entry.getKey();
            final int i = point.first;
            final int j = point.second;
            final int id = entry.getValue();

            final Consumer<Pair<Integer, Integer>> unitIfPresent = p -> {
                if (map.containsKey(p)) {
                    unionFind.unit(id, map.get(p));
                }
            };
            unitIfPresent.accept(new Pair<>(i - 1, j - 1));
            unitIfPresent.accept(new Pair<>(i - 1, j));
            unitIfPresent.accept(new Pair<>(i, j - 1));
            unitIfPresent.accept(new Pair<>(i, j + 1));
            unitIfPresent.accept(new Pair<>(i + 1, j));
            unitIfPresent.accept(new Pair<>(i + 1, j + 1));
        }

        final long answer = IntStream.range(0, n)
            .map(unionFind::getRoot)
            .distinct()
            .count();

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
