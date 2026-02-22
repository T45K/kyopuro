package ABC.ABC190.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
巡回セールスマン問題
2^N * N^2
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        final int k = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, k)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final boolean[] isVisited = new boolean[n + 1];
        dfs(list.get(0), graph, isVisited);
        final boolean isConnected = list.stream()
            .allMatch(i -> isVisited[i]);
        if (!isConnected) {
            System.out.println(-1);
            return;
        }

        final int[][] distances = new int[k][k];
        final Map<Integer, Integer> mapper = IntStream.range(0, k)
            .boxed()
            .collect(Collectors.toMap(list::get, Function.identity()));
        for (int i = 0; i < k; i++) {
            final int[] tmp = dijkstra(list.get(i), graph, n);
            for (final Map.Entry<Integer, Integer> entry : mapper.entrySet()) {
                distances[i][entry.getValue()] = tmp[entry.getKey()];
            }
        }
        final int[][] dp = new int[1 << k][k];
        for (final int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        for (int i = 0; i < k; i++) {
            dp[1 << i][i] = 0;
        }
        for (int i = 0; i < 1 << k; i++) {
            for (int j = 0; j < k; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int l = 0; l < k; l++) {
                    final int dest = i | (1 << l);
                    if (dest == i) {
                        continue;
                    }
                    dp[dest][l] = Math.min(dp[dest][l], dp[i][j] + distances[j][l]);
                }
            }
        }
        final int answer = Arrays.stream(dp[(1 << k) - 1], 0, k)
            .min()
            .orElseThrow() + 1;
        System.out.println(answer);
    }

    private static int[] dijkstra(final int start, final Map<Integer, List<Integer>> graph, final int n) {
        final int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        final PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.cost));
        for (final int next : getNoneNullList(graph, start)) {
            queue.add(new Pair(next, 1));
        }
        while (!queue.isEmpty()) {
            final Pair current = queue.poll();
            if (current.cost >= distances[current.to]) {
                continue;
            }
            distances[current.to] = current.cost;
            for (final int next : getNoneNullList(graph, current.to)) {
                if (current.cost + 1 >= distances[next]) {
                    continue;
                }
                queue.add(new Pair(next, current.cost + 1));
            }
        }
        return distances;
    }

    private static void dfs(final int current, final Map<Integer, List<Integer>> graph, final boolean[] isVisited) {
        isVisited[current] = true;
        for (final int next : getNoneNullList(graph, current)) {
            if (isVisited[next]) {
                continue;
            }
            dfs(next, graph, isVisited);
        }
    }

    private static <K, V> List<V> getNoneNullList(final Map<K, List<V>> map, final K key) {
        return Optional.ofNullable(map.get(key)).orElse(Collections.emptyList());
    }

    private static class Pair {
        final int to;
        final int cost;

        Pair(final int to, final int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    