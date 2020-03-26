package CodeFestival2016Final.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final UnionFindTree tree = new UnionFindTree(n);
        final int[] langToMan = new int[m + 1];
        Arrays.fill(langToMan, -1);
        for (int man = 1; man <= n; man++) {
            final int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                final int lang = scanner.nextInt();
                if (langToMan[lang] == -1) {
                    langToMan[lang] = man;
                } else {
                    tree.unit(man, langToMan[lang]);
                }
            }
        }

        final boolean answer = IntStream.rangeClosed(1, n)
                .mapToObj(tree::getRoot)
                .allMatch(v -> v == 1);

        System.out.println(answer ? "YES" : "NO");
    }

    private static class UnionFindTree {
        private final List<Integer> indices = new ArrayList<>();
        private Integer[] nodes;

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
}
