package other.aising2019.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
グラフ 経路系 Union_Findで殴るだけ
隣のマスの色が違うときに辺を張って，そこから到達できるマスを数える
UF木だとrootが同じとき到達可能と判断できる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final boolean[][] table = new boolean[h][w];
        final List<Integer> blacks = new ArrayList<>();
        final List<Integer> whites = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                if (s.charAt(j) == '#') {
                    table[i][j] = true;
                    blacks.add(flatten(i, j, w));
                } else {
                    whites.add(flatten(i, j, w));
                }
            }
        }

        final UnionFindTree tree = new UnionFindTree(h * w - 1);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i > 0 && table[i][j] != table[i - 1][j]) {
                    tree.unit(flatten(i, j, w), flatten(i - 1, j, w));
                }
                if (i < h - 1 && table[i][j] != table[i + 1][j]) {
                    tree.unit(flatten(i, j, w), flatten(i + 1, j, w));
                }
                if (j > 0 && table[i][j] != table[i][j - 1]) {
                    tree.unit(flatten(i, j, w), flatten(i, j - 1, w));
                }
                if (j < w - 1 && table[i][j] != table[i][j + 1]) {
                    tree.unit(flatten(i, j, w), flatten(i, j + 1, w));
                }
            }
        }

        final long[] count = new long[h * w];
        for (final int black : blacks) {
            count[tree.getRoot(black)]++;
        }

        final long answer = whites.stream()
                .mapToLong(i -> count[tree.getRoot(i)])
                .sum();
        System.out.println(answer);
    }

    private static int flatten(final int i, final int j, final int w) {
        return i * w + j;
    }

    private static class UnionFindTree {
        private final Integer[] nodes;
        private final List<Integer> indices = new ArrayList<>();

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        private Integer[] init(final int numOfNodes) {
            return IntStream.rangeClosed(0, numOfNodes)
                    .boxed()
                    .toArray(Integer[]::new);
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final Integer index : indices) {
                    nodes[index] = rootNode;
                }
                indices.clear();
                return nodeNumber;
            }

            indices.add(nodeNumber);
            return getRoot(rootNode);
        }

        void unit(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            if (rootA == rootB) {
                return;
            }

            nodes[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
    