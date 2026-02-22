package ABC.ABC239.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
たかだか20個の数を保存すれば良いので、ボトムアップで愚直に計算すればよい
 */
public class Main {
    private static int[] values;
    private static ListMap<Integer, Integer> tree;
    private static ListMap<Integer, Integer> top20Values;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        values = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = scanner.nextInt();
        }

        tree = new ListMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.putSingle(a, b);
            tree.putSingle(b, a);
        }

        top20Values = new ListMap<>();
        traverse(1, 0);

        final String answer = Stream.generate(() -> {
                final int v = scanner.nextInt();
                final int k = scanner.nextInt();
                return top20Values.get(v).get(k - 1);
            }).limit(q)
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static void traverse(final int current, final int parent) {
        final List<Integer> children = tree.getList(current);
        if (current != 1 && children.size() == 1) { // leaf
            top20Values.putSingle(current, values[current]);
            return;
        }

        final List<Integer> value = new ArrayList<>();
        value.add(values[current]);
        for (final int child : children) {
            if (child == parent) {
                continue;
            }
            traverse(child, current);
            value.addAll(top20Values.getList(child));
        }

        top20Values.put(current, value.stream()
            .sorted(Comparator.reverseOrder())
            .limit(20)
            .collect(Collectors.toList()));
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
