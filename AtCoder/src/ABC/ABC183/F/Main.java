package ABC.ABC183.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/*
mapをマージさせる発想
小さい方から大きい方にマージすると計算量がマシになるらしい
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            final int c = scanner.nextInt();
            final HashMap<Integer, Integer> tmp = new HashMap<>();
            tmp.put(c, 1);
            map.put(i, tmp);
        }

        final UnionFindTree tree = new UnionFindTree(n, map);
        for (int i = 0; i < q; i++) {
            final int query = scanner.nextInt();
            if (query == 1) {
                final int a = scanner.nextInt();
                final int b = scanner.nextInt();
                tree.unit(a, b);
                continue;
            }

            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            final int root = tree.getRoot(x);
            final int answer = map
                .getOrDefault(root, Collections.emptyMap())
                .getOrDefault(y, 0);
            System.out.println(answer);
        }
    }

    private static class UnionFindTree {
        private final int[] nodes;
        private final Deque<Integer> indices = new ArrayDeque<>();
        private final Map<Integer, Map<Integer, Integer>> map;

        UnionFindTree(final int numOfNodes, final Map<Integer, Map<Integer, Integer>> map) {
            this.nodes = IntStream.rangeClosed(0, numOfNodes).toArray();
            this.map = map;
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

            if (rootA == rootB) {
                return;
            }

            int smaller;
            int bigger;
            if (map.get(rootA).size() >= map.get(rootB).size()) {
                bigger = rootA;
                smaller = rootB;
            } else {
                bigger = rootB;
                smaller = rootA;
            }

            nodes[smaller] = bigger;
            final Map<Integer, Integer> biggerMap = map.get(bigger);
            for (final Map.Entry<Integer, Integer> entry : map.get(smaller).entrySet()) {
                final int key = entry.getKey();
                final int value = entry.getValue();
                biggerMap.merge(key, value, Integer::sum);
            }
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
