package ABC.ABC226.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;

/*
辺の数=頂点の数
を満たすグラフを数える

引数が多い関数で再帰をすると遅くなるので注意
 */
public class Main {
    private static final int MOD = 998_244_353;

    private static ListMap<Integer, Integer> graph;
    private static int nodeCount = 0;
    private static boolean[] isVisited;
    private static Set<Pair<Integer, Integer>> edges;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        graph = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            graph.putSingle(u, v);
            graph.putSingle(v, u);
        }

        long product = 1;
        isVisited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (isVisited[i]) {
                continue;
            }
            if (!graph.containsKey(i)) {
                System.out.println(0);
                return;
            }
            nodeCount = 1;
            edges = new HashSet<>();
            dfs(i, 0);
            if (nodeCount != edges.size()) {
                System.out.println(0);
                return;
            }
            product *= 2;
            product %= MOD;
        }
        System.out.println(product);
    }

    private static void dfs(final int current, final int parent) {
        isVisited[current] = true;
        for (final int next : graph.getList(current)) {
            if (next == parent) {
                continue;
            }

            final Pair<Integer, Integer> pair = new Pair<>(Math.min(current, next), Math.max(current, next));
            edges.add(pair);
            if (!isVisited[next]) {
                nodeCount++;
                dfs(next, current);
            }
        }
    }

    private static class ListMap<K, V> extends HashMap<K, List<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public List<V> getList(final K key) {
            return Optional.ofNullable(super.get(key)).orElse(Collections.emptyList());
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

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
