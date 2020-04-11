package AtCoder.ABC.ABC087.D;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        final MyList[] myLists = new MyList[n + 1];
        for (int i = 1; i <= n; i++) {
            myLists[i] = new MyList();
        }
        for (int i = 0; i < m; i++) {
            final int left = scanner.nextInt();
            final int right = scanner.nextInt();
            final int distance = scanner.nextInt();
            myLists[left].add(new Pair(right, distance));
            myLists[right].add(new Pair(left, -distance));
        }

        final boolean[] isVisited = new boolean[n + 1];
        final long[] position = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            if (isVisited[i]) {
                continue;
            }

            isVisited[i] = true;
            position[i] = 0;

            if (!dfs(isVisited, position, i, myLists)) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    private static boolean dfs(final boolean[] isVisited, final long[] distances, final int start, final MyList[] myLists) {
        isVisited[start] = true;
        boolean result = true;
        for (final Pair pair : myLists[start]) {
            if (isVisited[pair.destination] && distances[pair.destination] != distances[start] + pair.distance) {
                return false;
            }

            if (!isVisited[pair.destination]) {
                distances[pair.destination] = distances[start] + pair.distance;
                result &= dfs(isVisited, distances, pair.destination, myLists);
            }
        }

        return result;
    }

    static class MyList extends ArrayList<Pair> {
    }

    static class Pair {
        int destination;
        long distance;

        public Pair(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }
}
