package ABC.ABC074.D;

import java.util.Scanner;

/*
ワーシャルフロイド グラフ ワーシャルフロイドを思いつけばすぐ方針が立つ
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        final int[][] distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = true;
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    if (graph[i][j] == graph[i][k] + graph[k][j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    distances[i][j] = graph[i][j];
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                sum += distances[i][j];
            }
        }

        System.out.println(sum);
    }
}
