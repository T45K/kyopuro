package AtCoder.ABC.ABC097.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] indexToValue = new int[n];
        final int[] valueToIndex = new int[n];
        for (int i = 0; i < n; i++) {
            final int tmp = scanner.nextInt() - 1;
            indexToValue[i] = tmp;
            valueToIndex[tmp] = i;
        }

        final UnionFindTree tree = new UnionFindTree(n);
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;
            tree.unit(Math.min(a, b), Math.max(a, b));
        }

        final int[] nodes = tree.getNodes();
        for (int i = 0; i < n; i++) {
            if (indexToValue[i] == i || nodes[i] != nodes[indexToValue[i]]) {
                continue;
            }

            int index = valueToIndex[i];
            int value = indexToValue[i];
            valueToIndex[i] = i;
            indexToValue[i] = i;
            indexToValue[index] = value;
            valueToIndex[value] = index;
        }

        int answer = 0;
        for (int i = 0; i < indexToValue.length; i++) {
            if (indexToValue[i] == i) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static class UnionFindTree {
        private int[] nodes;
        private List<Integer> indices = new ArrayList<>();

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        int[] getNodes() {
            update();
            return nodes;
        }

        private void update() {
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = getRoot(i);
            }
        }

        private int[] init(final int numOfNodes) {
            final int[] array = new int[numOfNodes];
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }

            return array;
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final Integer index : indices) {
                    nodes[index] = rootNode;
                }
                indices = new ArrayList<>();
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

            nodes[rootA] = rootB;
        }
    }

    static class FastScanner {
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
