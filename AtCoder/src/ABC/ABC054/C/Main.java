package ABC.ABC054.C;

import java.util.ArrayList;
import java.util.Arrays;
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

            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        final boolean[] isVisited = new boolean[n + 1];
        final int answer = dfs(graph, 1, isVisited);
        System.out.println(answer);
    }

    private static int dfs(final Map<Integer, List<Integer>> graph, final int current, final boolean[] isVisited) {
        isVisited[current] = true;
        if (isVisitedAll(isVisited)) {
            return 1;
        }

        int sum = 0;
        for (final int next : graph.get(current)) {
            if (!isVisited[next]) {
                sum += dfs(graph, next, Arrays.copyOf(isVisited, isVisited.length));
            }
        }

        return sum;
    }

    private static boolean isVisitedAll(final boolean[] isVisited) {
        return IntStream.range(1, isVisited.length)
                .mapToObj(i -> isVisited[i])
                .allMatch(Boolean::booleanValue);
    }
}
