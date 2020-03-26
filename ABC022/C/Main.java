package ABC022.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final long[][] table = new long[n + 1][n + 1];
        for (final long[] array : table) {
            Arrays.fill(array, Long.MAX_VALUE / 2);
        }

        final List<Edge> connectFromOne = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int l = scanner.nextInt();

            if (a == 1) {
                connectFromOne.add(new Edge(b, l));
                continue;
            }

            table[a][b] = l;
            table[b][a] = l;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }

        long min = Long.MAX_VALUE / 2;
        for (int i = 0; i < connectFromOne.size(); i++) {
            final Edge start = connectFromOne.get(i);
            for (int j = i + 1; j < connectFromOne.size(); j++) {
                final Edge dest = connectFromOne.get(j);
                min = Math.min(min, start.length + table[start.to][dest.to] + dest.length);
            }
        }

        if (min == Long.MAX_VALUE / 2) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static class Edge {
        final int to;
        final long length;

        Edge(final int to, final long length) {
            this.to = to;
            this.length = length;
        }
    }
}
