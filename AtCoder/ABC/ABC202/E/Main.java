package AtCoder.ABC.ABC202.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
解説AC
「xがyの子孫である <=> in_y <= in_x < out_y」がなりたつ
 */
public class Main {

    private static int seq = 1;

    @SuppressWarnings("all")
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final ListMap<Integer, Integer> tree = new ListMap<>();
        for (int i = 2; i <= n; i++) {
            final int p = scanner.nextInt();
            tree.putSingle(p, i);
        }

        final ListMap<Integer, Integer> depthToIn = new ListMap<>();
        final Node[] nodes = new Node[n + 1];
        nodes[0] = new Node(Integer.MIN_VALUE, Integer.MAX_VALUE);
        dfs(1, 0, tree, depthToIn, nodes);

        final int q = scanner.nextInt();
        final String answer = Stream.generate(() -> {
                final int u = scanner.nextInt();
                final int d = scanner.nextInt();
                final Node node = nodes[u];
                final List<Integer> list = depthToIn.getList(d);
                final int begin = ~Collections.binarySearch(list, node.in, (a, b) -> a >= b ? 1 : -1);
                final int end = ~Collections.binarySearch(list, node.out, (a, b) -> a > b ? 1 : -1);
                return end - begin;
            })
            .limit(q)
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static void dfs(final int current, final int depth, final ListMap<Integer, Integer> tree, final ListMap<Integer, Integer> depthToIn, final Node[] nodes) {
        final int in = seq;
        depthToIn.putSingle(depth, in);
        seq++;
        for (final int next : tree.getList(current)) {
            dfs(next, depth + 1, tree, depthToIn, nodes);
        }
        final int out = seq;
        seq++;
        nodes[current] = new Node(in, out);
    }

    private static class Node {
        final int in;
        final int out;

        Node(final int in, final int out) {
            this.in = in;
            this.out = out;
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
}
