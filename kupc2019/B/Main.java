package kupc2019.B;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int w = scanner.nextInt();

        final Goods[] goods = new Goods[n];
        for (int i = 0; i < n; i++) {
            goods[i] = new Goods(scanner.nextInt(), scanner.nextInt());
        }

        final UnionFindTree unionFindTree = new UnionFindTree(n);
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;
            unionFindTree.unit(Math.max(a, b), Math.min(a, b));
        }

        final Map<Integer, Goods> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int root = unionFindTree.getRoot(i);
            map.computeIfAbsent(root, g -> new Goods())
                    .add(goods[i]);
        }

        final Goods[] unionGoods = new Goods[map.size()];
        map.values().toArray(unionGoods);

        final int[][] dpTable = new int[unionGoods.length][w + 1];

        for (int i = 0; i < w + 1; i++) {
            dpTable[0][i] = i >= unionGoods[0].wight ? unionGoods[0].value : 0;
        }

        for (int i = 1; i < unionGoods.length; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (j < unionGoods[i].wight) {
                    dpTable[i][j] = dpTable[i - 1][j];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j - unionGoods[i].wight] + unionGoods[i].value, dpTable[i - 1][j]);
                }
            }
        }

        System.out.println(getMaxValueFromIntArray(dpTable[unionGoods.length - 1]));
    }

    private static int getMaxValueFromIntArray(final int[] array) {
        int max = 0;
        for (final int i : array) {
            max = Math.max(i, max);
        }

        return max;
    }

    static class Goods {
        int wight;
        int value;

        Goods() {
        }

        Goods(final int wight, final int value) {
            this.wight = wight;
            this.value = value;
        }

        void add(final Goods goods) {
            this.wight += goods.wight;
            this.value += goods.value;
        }
    }

    static class UnionFindTree {
        private int[] nodes;

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
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
                return nodeNumber;
            }

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
}
