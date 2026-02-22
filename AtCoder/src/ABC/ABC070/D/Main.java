package ABC.ABC070.D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, List<Node>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            tree.computeIfAbsent(a, v -> new ArrayList<>()).add(new Node(b, c));
            tree.computeIfAbsent(b, v -> new ArrayList<>()).add(new Node(a, c));
        }

        final int q = scanner.nextInt();
        final int k = scanner.nextInt();

        final long[] distances = new long[n + 1];
        distances[k] = Long.MAX_VALUE;
        recursive(distances, tree, k, 0);

        for (int i = 0; i < q; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            System.out.println(distances[x] + distances[y]);
        }
    }

    private static void recursive(final long[] distances, final Map<Integer, List<Node>> tree, final int currentNode, final long sumDistance) {
        final List<Node> destinations = tree.get(currentNode);
        for (final Node destination : destinations) {
            if (distances[destination.label] != 0) {
                continue;
            }

            distances[destination.label] = sumDistance + destination.distance;
            recursive(distances, tree, destination.label, sumDistance + destination.distance);
        }
    }

    static class Node {
        int label;
        long distance;

        Node(final int label, final int distance) {
            this.label = label;
            this.distance = distance;
        }
    }
}
