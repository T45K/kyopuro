package ABC.ABC073.D;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int r = scanner.nextInt();

        final int[] toVisit = new int[r];
        for (int i = 0; i < r; i++) {
            toVisit[i] = scanner.nextInt() - 1;
        }

        final int[][] graph = new int[n][n];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE / 2);
            graph[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;
            final int c = scanner.nextInt();

            graph[a][b] = c;
            graph[b][a] = c;
        }

        warshallFloyd(graph);

        for (int i = 0; i < toVisit.length; i++) {
            final boolean[] isVisited = new boolean[r];
            isVisited[i] = true;
            permutation(toVisit, isVisited, toVisit[i], graph, 1, 0);
        }
        System.out.println(answer);
    }

    static int answer = Integer.MAX_VALUE;

    private static void permutation(final int[] toVisit, final boolean[] isVisited, final int current, final int[][] graph, final int index, final int sum) {
        if (index == isVisited.length) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < isVisited.length; i++) {
            if (isVisited[i]) {
                continue;
            }

            isVisited[i] = true;
            permutation(toVisit, isVisited, toVisit[i], graph, index + 1, sum + graph[current][toVisit[i]]);
            isVisited[i] = false;
        }
    }

    private static void warshallFloyd(final int[][] graph) {
        for (int k = 0; k < graph.length; k++) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }
}
