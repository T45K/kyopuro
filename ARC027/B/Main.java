package ARC027.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final char[] s1 = scanner.next().toCharArray();
        final char[] s2 = scanner.next().toCharArray();

        final int[] counts = new int[26];
        Arrays.fill(counts, Integer.MAX_VALUE);

        final FastUnionFindTree tree = new FastUnionFindTree(25);

        if (s1[0] >= 'A' && s2[0] >= 'A') {
            counts[s1[0] - 'A'] = 9;
            counts[s2[0] - 'A'] = 9;
            tree.unit(s1[0] - 'A', s2[0] - 'A');
        } else if (s1[0] >= 'A') {
            counts[s1[0] - 'A'] = 1;
        } else if (s2[0] >= 'A') {
            counts[s2[0] - 'A'] = 1;
        }

        for (int i = 1; i < n; i++) {
            final char a1 = s1[i];
            final char a2 = s2[i];
            if (a1 < 'A' && a2 < 'A') {
                continue;
            }

            final int i1 = a1 - 'A';
            final int i2 = a2 - 'A';

            if (a1 >= 'A' && a2 >= 'A') {
                counts[i1] = Math.min(10, counts[i1]);
                counts[i2] = Math.min(10, counts[i2]);
                tree.unit(i1, i2);
            } else if (a1 >= 'A') {
                counts[i1] = 1;
            } else {
                counts[i2] = 1;
            }
        }

        final Map<Integer, List<Integer>> group = new HashMap<>();
        for (int i = 0; i < counts.length; i++) {
            final int count = counts[i];
            if (count == Integer.MAX_VALUE) {
                continue;
            }

            final int root = tree.getRoot(i);
            group.computeIfAbsent(root, v -> new ArrayList<>()).add(count);
        }

        long answer = 1;
        for (final List<Integer> values : group.values()) {
            int min = Integer.MAX_VALUE;
            for (final Integer value : values) {
                min = Math.min(min, value);
            }
            answer *= min;
        }

        System.out.println(answer);
    }

    static class FastUnionFindTree {
        private final List<Integer> indices = new ArrayList<>();
        private Integer[] nodes;

        FastUnionFindTree(final int numOfNodes) {
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
