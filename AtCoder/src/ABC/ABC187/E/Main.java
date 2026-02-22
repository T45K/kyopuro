package ABC.ABC187.E;

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
解説AC
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Edge[] edges = new Edge[n];
        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 1; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            edges[i] = new Edge(a, b);
            tree.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }
        final int[] parent = new int[n + 1];
        decideParent(tree, 1, 0, parent);

        final int q = scanner.nextInt();
        final long[] cost = new long[n + 1];
        for (int i = 0; i < q; i++) {
            final int t = scanner.nextInt();
            final int e = scanner.nextInt();
            final int a;
            final int b;
            if (t == 1) {
                a = edges[e].a;
                b = edges[e].b;
            } else {
                a = edges[e].b;
                b = edges[e].a;
            }
            final int x = scanner.nextInt();
            if (parent[a] == b) {
                cost[a] += x;
            } else {
                cost[1] += x;
                cost[b] -= x;
            }
        }

        imos(tree, 1, 0, cost);
        final String answer = Arrays.stream(cost, 1, n + 1)
            .mapToObj(Long::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static void imos(final Map<Integer, List<Integer>> tree, final int current, final int previous, final long[] cost) {
        for (final int child : tree.get(current)) {
            if (child == previous) {
                continue;
            }
            cost[child] += cost[current];
            imos(tree, child, current, cost);
        }
    }

    private static void decideParent(final Map<Integer, List<Integer>> tree, final int current, final int previous, final int[] parent) {
        parent[current] = previous;
        for (final int child : tree.get(current)) {
            if (child == previous) {
                continue;
            }
            decideParent(tree, child, current, parent);
        }
    }

    private static class Edge {
        final int a;
        final int b;

        Edge(final int a, final int b) {
            this.a = a;
            this.b = b;
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
