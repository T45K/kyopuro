package AtCoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int q = scanner.nextInt();
        final ListMap<Integer, Pair> listMap = new ListMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int f = scanner.nextInt();
            listMap.putSingle(a, new Pair(b, f));
            listMap.putSingle(b, new Pair(a, f));
        }
        final Set<Integer> included = new LinkedHashSet<>();
        final TreeSet<Integer> sorted = new TreeSet<>();
        final StringJoiner answers = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final String operation = scanner.next();
            final int target = scanner.nextInt();
            if (operation.equals("+")) {
                for (final Pair pair : listMap.getList(target)) {
                    if (included.contains(pair.dest)) {
                        sorted.remove(pair.cost);
                    } else {
                        sorted.add(pair.cost);
                    }
                }
                included.add(target);
            } else {
                included.remove(target);
                for (final Pair pair : listMap.getList(target)) {
                    if (included.contains(pair.dest)) {
                        sorted.add(pair.cost);
                    } else {
                        sorted.remove(pair.cost);
                    }
                }
            }
            if (sorted.isEmpty()) {
                answers.add("0");
            } else {
                answers.add(sorted.last().toString());
            }
        }
        System.out.println(answers);
    }

    private static class Pair {
        final int dest;
        final int cost;

        Pair(final int dest, final int cost) {
            this.dest = dest;
            this.cost = cost;
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
