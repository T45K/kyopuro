package ARC.ARC111.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
解説AC
色を頂点，カードを辺としたグラフを構築したときに，
そのグラフの連結成分が
- 木だったら，木の頂点数-1
- そうでなければ，頂点数
を異なる色として利用できる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }
        final boolean[] isVisited = new boolean[400_001];
        final int sum = graph.keySet().stream()
            .filter(key -> !isVisited[key])
            .mapToInt(key -> {
                final Map<Integer, Count> connectedGraph = new HashMap<>();
                connectedGraph.put(key, new Count(0));
                dfs(graph, connectedGraph, key, 0);
                final int keySize = connectedGraph.keySet().size();
                final int valueSize = connectedGraph.values().stream().mapToInt(Count::get).sum();
                for (final int connectedGraphKey : connectedGraph.keySet()) {
                    isVisited[connectedGraphKey] = true;
                }
                return Math.min(keySize, valueSize);
            }).sum();
        System.out.println(sum);
    }

    private static void dfs(final Map<Integer, List<Integer>> graph, final Map<Integer, Count> connectedGraph, final int current, final int parent) {
        for (final int next : graph.get(current)) {
            if (next == parent) {
                continue;
            }
            if (connectedGraph.containsKey(next)) {
                connectedGraph.get(next).increment();
                continue;
            }
            connectedGraph.put(next, new Count());
            dfs(graph, connectedGraph, next, current);
        }
    }

    private static class Count {
        private int value;

        Count() {
            this.value = 1;
        }

        Count(int value) {
            this.value = value;
        }

        void increment() {
            value++;
        }

        int get() {
            return value;
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
