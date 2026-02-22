package ARC.ARC108.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
全域木にすることを考える
根を適当に決めた時，ある辺に対して
- 親がラベルの値の時，子は適当な値に
- 親がラベルの値でない時，子はラベルの値に
設定する
一度訪れた頂点を繋ぐ辺は間引いて良いので（全域木），
上の手順をdfsなどで行うと任意の与えられたグラフに対してよい書き込み方ができる
 */
public class Main {

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            final int c = scanner.nextInt();
            graph.computeIfAbsent(u, value -> new ArrayList<>()).add(new Edge(v, c));
            graph.computeIfAbsent(v, value -> new ArrayList<>()).add(new Edge(u, c));
        }
        final int[] array = new int[n + 1];
        array[1] = 1;
        dfs(graph, array, 1, 0, n);
        final String answer = Arrays.stream(array, 1, array.length)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static void dfs(final Map<Integer, List<Edge>> graph, final int[] array, final int current, final int parent, final int n) {
        for (final Edge edge : graph.get(current)) {
            if (edge.node == parent || array[edge.node] > 0) {
                continue;
            }
            array[edge.node] = array[current] == edge.label ? edge.label % n + 1 : edge.label;
            dfs(graph, array, edge.node, current, n);
        }
    }

    private static class Edge {
        final int node;
        final int label;

        Edge(final int node, final int label) {
            this.node = node;
            this.label = label;
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
