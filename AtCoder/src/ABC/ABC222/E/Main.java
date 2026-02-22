package ABC.ABC222.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
以下に注意
- 通らない辺に関しては何色でも良い
- 必ずしも移動するとは限らない
- つまり，まったく移動しないというパターンもある
 */
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) throws InterruptedException {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(m)
            .mapToInt(Integer::intValue)
            .toArray();
        final ListMap<Integer, Integer> tree = new ListMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            tree.putSingle(u, v);
            tree.putSingle(v, u);
        }

        final Map<Pair<Integer, Integer>, Integer> counts = new LinkedHashMap<>();
        for (int i = 0; i < m - 1; i++) {
            final Deque<Integer> queue = getShortestPath(array[i], array[i + 1], tree);
            if (queue.isEmpty()) {
                throw new RuntimeException();
            }
            int tmp = queue.pollFirst();
            while (!queue.isEmpty()) {
                final int next = queue.pollFirst();
                counts.compute(new Pair<>(Math.min(tmp, next), Math.max(tmp, next)), (key, v) -> v == null ? 1 : v + 1);
                tmp = next;
            }
        }
        if (counts.isEmpty()) {
            if (k == 0) {
                System.out.println(pow(n - 1));
            } else {
                System.out.println(0);
            }
            return;
        }

        final List<Pair<Integer, Integer>> keys = new ArrayList<>(counts.keySet());
        Map<Integer, Long> diffToCount = new LinkedHashMap<>();
        diffToCount.put(counts.get(keys.get(0)), 1L);
        diffToCount.put(-counts.get(keys.get(0)), 1L);
        for (int i = 1; i < keys.size(); i++) {
            final int count = counts.get(keys.get(i));
            final Map<Integer, Long> tmp = new LinkedHashMap<>();
            for (final Map.Entry<Integer, Long> entry : diffToCount.entrySet()) {
                tmp.compute(entry.getKey() + count, (key, v) -> v == null ? entry.getValue() : ((v + entry.getValue()) % MOD));
                tmp.compute(entry.getKey() - count, (key, v) -> v == null ? entry.getValue() : ((v + entry.getValue()) % MOD));
            }
            diffToCount = tmp;
        }
        System.out.println(Optional.ofNullable(diffToCount.get(k)).orElse(0L) * pow(n - 1 - keys.size()) % MOD);
    }

    private static long pow(final long count) {
        long tmp = 1;
        for (long i = 0; i < count; i++) {
            tmp *= 2;
            tmp %= MOD;
        }
        return tmp;
    }

    private static Deque<Integer> getShortestPath(final int a, final int b, final ListMap<Integer, Integer> tree) {
        final Deque<Integer> queue = new ArrayDeque<>();
        dfs(0, a, b, tree, queue);
        return queue;
    }

    private static void dfs(final int parent, final int current, final int target, final ListMap<Integer, Integer> tree, final Deque<Integer> queue) {
        if (current == target) {
            queue.add(current);
            return;
        }
        for (final int next : tree.getList(current)) {
            if (next == parent) {
                continue;
            }
            dfs(current, next, target, tree, queue);
            if (!queue.isEmpty() && queue.peekLast() == next) {
                queue.add(current);
                return;
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
