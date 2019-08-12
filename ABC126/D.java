package ABC126;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class D {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        Map<Integer, List<NodeAndDistance>> distanceMap = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            final int distance = scanner.nextInt();

            distanceMap.computeIfAbsent(x, e -> new ArrayList<>())
                    .add(new NodeAndDistance(y, distance));
            distanceMap.computeIfAbsent(y, e -> new ArrayList<>())
                    .add(new NodeAndDistance(x, distance));
        }

        final int[] array = new int[n];
        final boolean[] checked = new boolean[n];
        recursive(distanceMap, distanceMap.get(1), 0, array, checked);

        for (final int i : array) {
            System.out.println(i);
        }
    }

    private static void recursive(final Map<Integer, List<NodeAndDistance>> distanceMap, final List<NodeAndDistance> nodeAndDistanceList, final int distanceFormParent, final int[] array, final boolean[] checked) {
        for (final NodeAndDistance nodeAndDistance : nodeAndDistanceList) {
            final int node = nodeAndDistance.getNode();
            if (node == 1 || checked[node - 1]) {
                continue;
            }

            final int distance = nodeAndDistance.getDistance() + distanceFormParent;
            array[node - 1] = distance % 2;
            checked[node - 1] = true;
            recursive(distanceMap, distanceMap.get(node), distance, array, checked);
        }
    }

    static class NodeAndDistance {
        private final int node;
        private final int distance;

        NodeAndDistance(final int node, final int distance) {
            this.node = node;
            this.distance = distance % 2;
        }

        int getNode() {
            return node;
        }

        int getDistance() {
            return distance;
        }
    }
}
