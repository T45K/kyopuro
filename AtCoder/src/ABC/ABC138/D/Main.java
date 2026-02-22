package ABC.ABC138.D;

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
        final int q = scanner.nextInt();
        final Node[] nodes = new Node[n];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;

            nodes[a].addRelation(nodes[b]);
            nodes[b].addRelation(nodes[a]);
        }

        for (int i = 0; i < q; i++) {
            final int node = scanner.nextInt() - 1;
            final int operation = scanner.nextInt();
            nodes[node].addValue(operation);
        }

        recursive(nodes[0], null);

        for (final Node node : nodes) {
            System.out.print(node.getValue() + " ");
        }
    }

    private static void recursive(final Node node, final Node parent) {
        for (final Node relatedNode : node.getRelatedNodeList()) {
            if (relatedNode == parent) {
                continue;
            }

            relatedNode.addValue(node.getValue());

            recursive(relatedNode, node);
        }
    }

    static class Node {
        private int value;
        private final List<Node> relatedNodeList = new ArrayList<>();

        void addValue(final int value) {
            this.value += value;
        }

        int getValue() {
            return value;
        }

        void addRelation(final Node node) {
            relatedNodeList.add(node);
        }

        List<Node> getRelatedNodeList() {
            return relatedNodeList;
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

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
