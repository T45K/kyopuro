package AtCoder.ABC.ABC126.E;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

/*
簡単 Union-Find木で殴るだけ
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final UnionFindTree tree = new UnionFindTree(n + 1);
        final boolean[] exists = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.unit(a, b);
            exists[a] = true;
            exists[b] = true;
            scanner.nextInt();
        }

        final Set<Integer> countSet = new HashSet<>();
        final long count = IntStream.rangeClosed(1, n)
                .mapToObj(i -> exists[i])
                .filter(b -> !b)
                .count();

        IntStream.rangeClosed(1, n)
                .filter(i -> exists[i])
                .mapToObj(tree::getRoot)
                .forEach(countSet::add);

        System.out.println(count + countSet.size());
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
