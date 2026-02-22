package ARC.ARC011.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
文字列操作に見せかけたグラフ ダイクストラで殴る 経路復元が面倒臭すぎる
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String first = scanner.next();
        final String last = scanner.next();
        if (first.equals(last) || isMovable(first, last)) {
            System.out.println(0);
            System.out.println(first);
            System.out.println(last);
            return;
        }

        final int n = scanner.nextInt();
        final String[] array = new String[n + 2];
        array[0] = first;
        array[1] = last;
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 2; i < n + 2; i++) {
            final String s = scanner.next();
            array[i] = s;
            for (int j = 0; j < i; j++) {
                if (isMovable(s, array[j])) {
                    graph.computeIfAbsent(i, v -> new ArrayList<>()).add(j);
                    graph.computeIfAbsent(j, v -> new ArrayList<>()).add(i);
                }
            }
        }

        final PriorityQueue<Destination> queue = new PriorityQueue<>(Comparator.comparingInt(d -> d.distance));
        queue.addAll(createDijkstraDestination(graph, 0, 0));
        final int[] distances = new int[n + 2];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
        while (!queue.isEmpty()) {
            final Destination dest = queue.poll();
            if (dest.distance >= distances[dest.node]) {
                continue;
            }
            distances[dest.node] = dest.distance;
            queue.addAll(createDijkstraDestination(graph, dest.node, dest.distance));
        }

        if (distances[1] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(distances[1] - 1);
        final int[] route = new int[distances[1] - 1];
        int tmpDistance = distances[1] - 1;
        int tmpNode = 1;
        for (int i = 0; i < route.length; i++) {
            for (final int next : graph.get(tmpNode)) {
                if (distances[next] == tmpDistance) {
                    tmpDistance--;
                    tmpNode = next;
                    route[i] = next;
                }
            }
        }

        System.out.println(first);
        for (int i = route.length - 1; i >= 0; i--) {
            System.out.println(array[route[i]]);
        }
        System.out.println(last);
    }

    private static List<Destination> createDijkstraDestination(final Map<Integer, List<Integer>> graph, final int current, final int distance) {
        return graph.get(current).stream()
                .map(i -> new Destination(i, distance + 1))
                .collect(Collectors.toList());
    }

    private static boolean isMovable(final String a, final String b) {
        boolean flag = false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (flag) {
                    return false;
                } else {
                    flag = true;
                }
            }
        }
        return true;
    }

    static class Destination {
        final int node;
        final int distance;

        Destination(final int node, final int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
