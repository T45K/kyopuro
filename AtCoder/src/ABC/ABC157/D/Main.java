package ABC.ABC157.D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();

        final int[] friendsArray = new int[n + 1];
        final FastUnionFindTree tree = new FastUnionFindTree(n);
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            friendsArray[a]++;
            friendsArray[b]++;
            tree.unit(Math.min(a, b), Math.max(a, b));
        }

        final int[] blocksArray = new int[n + 1];
        for (int i = 0; i < k; i++) {
            final int c = scanner.nextInt();
            final int d = scanner.nextInt();

            if (tree.isSame(c, d)) {
                blocksArray[c]++;
                blocksArray[d]++;
            }
        }

        final Map<Integer, List<Integer>> preFriendMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            final int root = tree.getRoot(i);
            preFriendMap.computeIfAbsent(root, v -> new ArrayList<>()).add(i);
        }

        final int[] answers = new int[n + 1];
        for (final List<Integer> value : preFriendMap.values()) {
            for (final int i : value) {

                answers[i] = value.size() - friendsArray[i] - blocksArray[i] - 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(answers[i] + " ");
        }
    }

    static class FastUnionFindTree {
        private int[] nodes;
        private final List<Integer> indices = new ArrayList<>();

        FastUnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
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
                for (final Integer index : indices) {
                    nodes[index] = rootNode;
                }
                indices.clear();
                return nodeNumber;
            }

            indices.add(nodeNumber);
            return getRoot(rootNode);
        }

        boolean isSame(final int nodeA, final int nodeB) {
            return getRoot(nodeA) == getRoot(nodeB);
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
