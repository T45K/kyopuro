package other.typecal90.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final BiFunction<Integer, Integer, Integer> convert = (a, b) -> a * (w + 1) + b;
        final boolean[] isRed = new boolean[(h + 1) * (w + 1)];
        final UnionFindTree tree = new UnionFindTree((h + 1) * (w + 1));
        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            final int tmp = scanner.nextInt();
            if (tmp == 1) {
                final int r = scanner.nextInt();
                final int c = scanner.nextInt();
                final int point = convert.apply(r, c);
                isRed[point] = true;
                if (r > 1 && isRed[convert.apply(r - 1, c)]) {
                    tree.unit(point, convert.apply(r - 1, c));
                }
                if (r < h && isRed[convert.apply(r + 1, c)]) {
                    tree.unit(point, convert.apply(r + 1, c));
                }
                if (c > 1 && isRed[convert.apply(r, c - 1)]) {
                    tree.unit(point, convert.apply(r, c - 1));
                }
                if (c < w && isRed[convert.apply(r, c + 1)]) {
                    tree.unit(point, convert.apply(r, c + 1));
                }
            } else {
                final int ra = scanner.nextInt();
                final int ca = scanner.nextInt();
                final int a = ra * (w + 1) + ca;
                final int rb = scanner.nextInt();
                final int cb = scanner.nextInt();
                final int b = rb * (w + 1) + cb;
                final String answer = isRed[a] && isRed[b] && tree.isSame(a, b) ? "Yes" : "No";
                System.out.println(answer);
            }
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

        long nextLong() {
            return Long.parseLong(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken("\n");
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
