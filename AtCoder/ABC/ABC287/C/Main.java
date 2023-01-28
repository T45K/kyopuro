package AtCoder.ABC.ABC287.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        if (m != n - 1) {
            System.out.println("No");
            return;
        }

        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.computeIfAbsent(u, ignored -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, ignored -> new ArrayList<>()).add(u);
        }

        final List<Integer> counts = IntStream.rangeClosed(1, n)
            .map(i -> graph.getOrDefault(i, Collections.emptyList()).size())
            .boxed()
            .collect(Collectors.toList());
        final List<Integer> nodesHavingOneEdge = counts.stream().filter(it -> it == 1).collect(Collectors.toList());
        final List<Integer> nodesHavingTwoEdge = counts.stream().filter(it -> it == 2).collect(Collectors.toList());
        if (!(nodesHavingOneEdge.size() == 2 && nodesHavingTwoEdge.size() == n - 2)) {
            System.out.println("No");
            return;
        }

        final boolean[] isVisited = new boolean[n + 1];
        dfs(nodesHavingOneEdge.get(0), graph, isVisited);
        final boolean isPathGraph = IntStream.rangeClosed(1, n).allMatch(it -> isVisited[it]);
        if (isPathGraph) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void dfs(final int current, final Map<Integer, List<Integer>> graph, final boolean[] isVisited) {
        isVisited[current] = true;
        for (final int next : graph.get(current)) {
            if (!isVisited[next]) {
                dfs(next, graph, isVisited);
            }
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
