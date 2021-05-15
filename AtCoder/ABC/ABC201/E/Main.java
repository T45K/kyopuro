package AtCoder.ABC.ABC201.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Edge>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            final long w = scanner.nextLong();
            tree.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, w));
            tree.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, w));
        }

        final long[] cost = new long[n + 1];
        dfs(tree, 1, 0, cost);

    }

    private static void dfs(final Map<Integer, List<Edge>> tree, final int current, final int parent, final long[] cost) {
        for (final Edge next : tree.get(current)) {
            if (next.to == parent) {
                continue;
            }
            cost[next.to] = cost[current] ^ next.cost;
            dfs(tree, next.to, current, cost);
        }
    }

    private static class Edge {
        final int to;
        final long cost;

        Edge(final int to, final long cost) {
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
