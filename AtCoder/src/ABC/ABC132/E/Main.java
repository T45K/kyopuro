package ABC.ABC132.E;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
グラフ 最短経路 重みなし グラフの状態を拡張するという発想 BFS
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.computeIfAbsent(u * 3, list -> new ArrayList<>()).add(v * 3 + 1);
            graph.computeIfAbsent(u * 3 + 1, list -> new ArrayList<>()).add(v * 3 + 2);
            graph.computeIfAbsent(u * 3 + 2, list -> new ArrayList<>()).add(v * 3);
        }

        final int s = scanner.nextInt() * 3;
        final int t = scanner.nextInt() * 3;
        final int[] distances = new int[(n + 1) * 3];
        Arrays.fill(distances, -1);
        distances[s] = 0;
        final Deque<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            final int current = queue.pollFirst();
            final List<Integer> list = graph.get(current);
            if (list == null) {
                continue;
            }
            for (final int next : list) {
                if (distances[next] >= 0) {
                    continue;
                }
                distances[next] = distances[current] + 1;
                queue.add(next);
            }
        }

        System.out.println(distances[t] >= 0 ? distances[t] / 3 : -1);
    }
}
