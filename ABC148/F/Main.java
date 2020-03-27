package ABC148.F;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int tVertex = scanner.nextInt();
        final int aVertex = scanner.nextInt();

        final List<Integer>[] tree = new List[n + 1];
        for (int i = 1; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree[a].add(b);
            tree[b].add(a);
        }

        final List<Integer> route = new ArrayList<>();
        final boolean[] isVisited = new boolean[n + 1];
        search(tree, aVertex, tVertex, route, isVisited);

        Arrays.fill(isVisited, false);
        final int upper = (route.size() - 1) / 2;
        if (upper + 1 < route.size()) {
            final int selectable = route.get(upper + 1);
            for (final int vertex : tree[route.get(upper)]) {
                if (vertex == selectable) {
                    continue;
                }
                isVisited[vertex] = true;
            }
        }

        final int longestDistance = searchLongest(tree, route.get(upper), 0, isVisited);

        System.out.println(longestDistance + upper - 1);
    }

    private static int searchLongest(final List<Integer>[] tree, final int current, final int distance, final boolean[] isVisited) {
        isVisited[current] = true;
        int longest = distance;
        for (final int next : tree[current]) {
            if (isVisited[next]) {
                continue;
            }

            final int tmp = searchLongest(tree, next, distance + 1, isVisited);
            if (tmp > longest) {
                longest = tmp;
            }
        }
        return longest;
    }

    private static boolean search(final List<Integer>[] tree, final int current, final int dest, final List<Integer> route, final boolean[] isVisited) {
        isVisited[current] = true;
        route.add(current);
        if (current == dest) {
            return true;
        }
        for (final int next : tree[current]) {
            if (isVisited[next]) {
                continue;
            }

            if (search(tree, next, dest, route, isVisited)) {
                return true;
            }
        }
        route.remove(route.size() - 1);
        return false;
    }
}
