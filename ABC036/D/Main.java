package ABC036.D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
木の塗り分け DP 解説AC なぜか通らなかった 根から遠いところから値を決めていく
 */
public class Main {
    private static long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        final boolean[] isVisited = new boolean[n + 1];
        final long[][] sums = new long[n + 1][2];
        dfs(graph, 1, isVisited, sums);

        System.out.println((sums[1][0] + sums[1][1]) % MOD);
    }

    private static void dfs(final Map<Integer, List<Integer>> graph, final int current, final boolean[] isVisited, final long[][] sums) {
        isVisited[current] = true;
        final List<Integer> nextList = graph.get(current);
        final List<Integer> unvisitedNode = nextList.stream()
                .filter(i -> !isVisited[i])
                .collect(Collectors.toList());

        if (unvisitedNode.size() == 0) {
            sums[current][0] = 1;
            sums[current][1] = 1;
            return;
        }

        long product = 1;
        long productWhite = 1;
        for (final int next : unvisitedNode) {
            dfs(graph, next, isVisited, sums);

            product *= (sums[next][0] + sums[next][1]) % MOD;
            product %= MOD;
            productWhite *= sums[next][0];
            productWhite %= MOD;
        }
        sums[current][0] = product;
        sums[current][0] %= MOD;
        sums[current][1] = productWhite % MOD;
        sums[current][1] %= MOD;
    }
}
