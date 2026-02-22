package ABC.ABC120;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class D {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int numOfIslands = scanner.nextInt();
        final int numOfBridges = scanner.nextInt();

        final List<Tuple<Integer, Integer>> bridges = new ArrayList<>();
        for (int i = 0; i < numOfBridges; i++) {
            bridges.add(new Tuple<>(scanner.nextInt() - 1, scanner.nextInt() - 1));
        }

        final List<Long> answerList = new ArrayList<>();
        long result = (long) numOfIslands * ((long) numOfIslands - 1) / 2;
        answerList.add(result);
        final UnionFindTree tree = new UnionFindTree(numOfIslands);

        for (int i = bridges.size() - 1; i >= 0; i--) {
            final Tuple<Integer, Integer> bridge = bridges.get(i);
            final Integer bridgeA = bridge.getFormer();
            final Integer bridgeB = bridge.getLatter();
            if (!tree.isSame(bridge.getFormer(), bridge.getLatter())) {
                final int treeSizeA = tree.getTreeSize(bridgeA);
                final int treeSizeB = tree.getTreeSize(bridgeB);
                result -= (treeSizeA * treeSizeB);
                tree.unit(bridgeA, bridgeB);
            }

            answerList.add(result);
        }
        for (int i = answerList.size() - 2; i >= 0; i--) {
            final Long answer = answerList.get(i);
            System.out.println(answer);
        }
    }

    static class Tuple<F, L> {
        private final F former;
        private final L latter;

        Tuple(final F former, final L latter) {
            this.former = former;
            this.latter = latter;
        }

        F getFormer() {
            return former;
        }

        L getLatter() {
            return latter;
        }
    }

    static class UnionFindTree {
        private final int[] nodes;
        private final List<Integer> rootUpdater = new ArrayList<>();
        private final Map<Integer, Integer> treeSizeMap;

        UnionFindTree(final int numOfNodes) {
            this.nodes = initNodes(numOfNodes);
            this.treeSizeMap = initMap(numOfNodes);
        }

        private int[] initNodes(final int numOfNodes) {
            final int[] array = new int[numOfNodes];
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }

            return array;
        }

        private Map<Integer, Integer> initMap(final int numOfNodes) {
            final Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < numOfNodes; i++) {
                map.put(i, 1);
            }
            return map;
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final Integer node : rootUpdater) {
                    nodes[node] = rootNode;
                }
                rootUpdater.clear();
                return nodeNumber;
            }

            rootUpdater.add(nodeNumber);
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

            final int sizeA = treeSizeMap.get(rootA);
            treeSizeMap.put(rootB, treeSizeMap.get(rootB) + sizeA);
            treeSizeMap.remove(rootA);
            nodes[rootA] = rootB;
        }

        int getTreeSize(final int nodeNumber) {
            return treeSizeMap.get(getRoot(nodeNumber));
        }
    }
}
