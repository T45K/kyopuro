package ABC073.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int r = scanner.nextInt();

        final int[] toVisit = new int[r];
        for (int i = 0; i < r; i++) {
            toVisit[i] = scanner.nextInt() - 1;
        }

        final int[][] cities = new int[n][n];
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;
            final int c = scanner.nextInt();
            cities[a][b] = c;
            cities[b][a] = c;
        }

        int answer = Integer.MAX_VALUE;
        for (int startIndex = 0; startIndex < r; startIndex++) {
            for (int destinationIndex = startIndex + 1; destinationIndex < r; destinationIndex++) {
                final int start = toVisit[startIndex];
                final int destination = toVisit[destinationIndex];
                final boolean[] isVisited = new boolean[n];
                isVisited[start] = true;
                final List<Integer> route = new ArrayList<>();
                route.add(start);
                final Set<Integer> sets = Arrays.stream(toVisit, 0, toVisit.length)
                        .boxed()
                        .filter(e -> e != start && e != destination)
                        .collect(Collectors.toSet());

                answer = Math.min(answer, recursive(start, destination, route, isVisited, cities, sets));
            }
        }

        System.out.println(answer);
    }

    private static int recursive(final int current, final int destination, final List<Integer> route, final boolean[] isVisited, final int[][] cities, final Set<Integer> sets) {
        if (current == destination) {
            if (!sets.isEmpty()) {
                return Integer.MAX_VALUE;
            }
            int sum = 0;
            for (int i = 0; i < route.size() - 1; i++) {
                final Integer prev = route.get(i);
                final Integer next = route.get(i + 1);
                if (cities[prev][next] == 0) {
                    return Integer.MAX_VALUE;
                }
                sum += cities[prev][next];
            }
            return sum;
        }

        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i]) {
                continue;
            }

            isVisited[i] = true;
            route.add(i);
            final boolean isIncluded = sets.remove(i);
            sum = Math.min(sum, recursive(i, destination, route, isVisited, cities, sets));
            isVisited[i] = false;
            route.remove(route.size() - 1);
            if (isIncluded) {
                sets.add(i);
            }
        }

        return sum;
    }
}
