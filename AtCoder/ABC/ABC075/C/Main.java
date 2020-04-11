package AtCoder.ABC.ABC075.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            graph.computeIfAbsent(a, v -> new ArrayList<>())
                    .add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>())
                    .add(a);
        }

        int counter = 0;
        for (final Map.Entry<Integer, List<Integer>> map : graph.entrySet()) {
            final Integer key = map.getKey();
            final List<Integer> value = map.getValue();
            for (int i = 0; i < value.size(); i++) {
                final int node = value.get(0);
                value.remove(0);
                final boolean[] isVisited = new boolean[n + 1];
                dfs(graph, isVisited, key);
                if (!isAllTrue(isVisited)) {
                    counter++;
                }
                value.add(node);
            }
        }

        System.out.println(counter / 2);
    }

    private static void dfs(final Map<Integer, List<Integer>> graph, final boolean[] isVisited, final int vertex) {
        isVisited[vertex] = true;
        for (final int destination : graph.get(vertex)) {
            if (!isVisited[destination]) {
                dfs(graph, isVisited, destination);
            }
        }
    }

    private static boolean isAllTrue(final boolean[] array) {
        return IntStream.range(1, array.length)
                .mapToObj(i -> array[i])
                .allMatch(Boolean::booleanValue);
    }
}
