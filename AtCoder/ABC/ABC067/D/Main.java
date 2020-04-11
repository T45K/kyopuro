package AtCoder.ABC.ABC067.D;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final int BLACK = 1;
    private static final int WHITE = -1;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            tree.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        final boolean[] isVisited = new boolean[n + 1];
        isVisited[1] = true;
        final Deque<Integer> route = search(tree, isVisited, 1, n);

        final List<Integer> fennecRoute = new ArrayList<>();
        final List<Integer> snukeRoute = new ArrayList<>();
        final int[] visitable = new int[n + 1];
        while (!route.isEmpty()) {
            final int fennecNext = route.pollFirst();
            visitable[fennecNext] = BLACK;
            fennecRoute.add(fennecNext);

            if (route.isEmpty()) break;

            final int snukeNext = route.pollLast();
            visitable[snukeNext] = WHITE;
            snukeRoute.add(snukeNext);
        }

        for (final int toVisit : fennecRoute) {
            visitable[toVisit] = BLACK;
            dfs(tree, visitable, toVisit, BLACK);
        }
        for (final int toVisit : snukeRoute) {
            visitable[toVisit] = WHITE;
            dfs(tree, visitable, toVisit, WHITE);
        }

        int fennecCount = 0;
        int snukeCount = 0;
        for (int i = 1; i <= n; i++) {
            if (visitable[i] == BLACK) fennecCount++;
            else snukeCount++;
        }

        System.out.println(fennecCount > snukeCount ? "Fennec" : "Snuke");
    }

    private static void dfs(final Map<Integer, List<Integer>> tree, final int[] visitable, final int current, final int color) {
        for (final int next : tree.get(current)) {
            if (visitable[next] == 0) {
                visitable[next] = color;
                dfs(tree, visitable, next, color);
            }
        }
    }

    private static Deque<Integer> search(final Map<Integer, List<Integer>> tree, final boolean[] isVisited, final int current, final int dest) {
        if (current == dest) {
            final Deque<Integer> route = new ArrayDeque<>();
            route.add(dest);
            return route;
        }

        for (final int next : tree.get(current)) {
            if (!isVisited[next]) {
                isVisited[next] = true;
                final Deque<Integer> route = search(tree, isVisited, next, dest);
                if (!route.isEmpty()) {
                    route.addFirst(current);
                    return route;
                }
            }
        }

        return new ArrayDeque<>();
    }
}
