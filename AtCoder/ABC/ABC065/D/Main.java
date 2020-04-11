package AtCoder.ABC.ABC065.D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Point> points = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            points.add(new Point(i, scanner.nextInt(), scanner.nextInt()));
        }

        final List<Edge> edges = new ArrayList<>();
        points.sort(Comparator.comparingLong(o -> o.x));
        Point previous = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            final Point current = points.get(i);
            edges.add(new Edge(Math.min(previous.label, current.label), Math.max(previous.label, current.label), current.x - previous.x));
            previous = current;
        }

        points.sort(Comparator.comparingLong(o -> o.y));
        previous = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            final Point current = points.get(i);
            edges.add(new Edge(Math.min(previous.label, current.label), Math.max(previous.label, current.label), current.y - previous.y));
            previous = current;
        }

        edges.sort(Comparator.comparingLong(o -> o.cost));
        final FastUnionFindTree unionFindTree = new FastUnionFindTree(n);
        long sum = 0;
        for (final Edge edge : edges) {
            if (!unionFindTree.isSame(edge.label1, edge.label2)) {
                unionFindTree.unit(edge.label1, edge.label2);
                sum += edge.cost;
            }
        }

        System.out.println(sum);
    }

    static class Point {
        int label;
        long x, y;

        Point(final int label, final int x, final int y) {
            this.label = label;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        int label1, label2;
        long cost;

        public Edge(final int label1, final int label2, final long cost) {
            this.label1 = label1;
            this.label2 = label2;
            this.cost = cost;
        }
    }

    static class FastUnionFindTree {
        private int[] nodes;
        private List<Integer> indices = new ArrayList<>();

        FastUnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        public int[] getNodes() {
            return nodes;
        }

        private int[] init(final int numOfNodes) {
            final int[] array = new int[numOfNodes + 1];
            for (int i = 1; i <= numOfNodes; i++) {
                array[i] = i;
            }

            return array;
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final int index : indices) {
                    nodes[index] = rootNode;
                }
                indices = new ArrayList<>();
                return nodeNumber;
            }

            indices.add(nodeNumber);
            return getRoot(rootNode);
        }

        boolean isSame(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            return rootA == rootB;
        }

        void unit(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            if (rootA == rootB) {
                return;
            }

            nodes[rootB] = rootA;
        }
    }
}
