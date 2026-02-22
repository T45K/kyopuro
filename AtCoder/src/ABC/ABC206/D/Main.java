package ABC.ABC206.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
対応している数字を繋げてグラフを作ろと，連結グラフ中の数字は全て同じ値に揃えなければならない
連結グラフの頂点数-1の和
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final SetMap<Integer, Integer> map = new SetMap<>();
        for (int i = 0; i < n / 2; i++) {
            final int pre = list.get(i);
            final int post = list.get(n - 1 - i);
            if (pre == post) {
                continue;
            }
            map.putSingle(pre, post);
            map.putSingle(post, pre);
        }

        final int[] array = new int[200_001];
        for (int i = 1; i <= 200_000; i++) {
            if (array[i] > 0 || !map.containsKey(i)) {
                continue;
            }

            dfs(map, i, array, i);
        }

        final long answer = Arrays.stream(array, 1, array.length)
            .filter(v -> v > 0)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .values().stream()
            .mapToLong(v -> v - 1)
            .sum();
        System.out.println(answer);
    }

    private static void dfs(final SetMap<Integer, Integer> map, final int current, final int[] array, final int value) {
        if (array[current] > 0) {
            return;
        }

        array[current] = value;
        for (final int next : map.getList(current)) {
            dfs(map, next, array, value);
        }
    }

    private static class SetMap<K, V> extends LinkedHashMap<K, Set<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new LinkedHashSet<>()).add(value);
        }

        public Set<V> getList(final K key) {
            return Optional.ofNullable(super.get(key)).orElse(Collections.emptySet());
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
