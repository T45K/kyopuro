package other.tokiomarine2020.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    private static final Map<Integer, Map<Integer, Integer>> tree = new HashMap<>();
    private static Item[] items;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        items = new Item[n + 1];
        for (int i = 1; i <= n; i++) {
            items[i] = new Item(scanner.nextInt(), scanner.nextInt());
        }

        final Map<Integer, Integer> initial = new HashMap<>();
        initial.put(0, 0);
        initial.put(items[1].weight, items[1].value);
        tree.put(1, initial);

        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            final int v = scanner.nextInt();
            final int l = scanner.nextInt();
            System.out.println(entry(v, l));
        }
    }

    private static int entry(final int current, final int l) {
        if (tree.containsKey(current)) {
            return tree.get(current).entrySet().stream()
                .filter(e -> e.getKey() <= l)
                .mapToInt(Map.Entry::getValue)
                .max()
                .orElseThrow();
        }

        final int parent = current / 2;
        if (!tree.containsKey(parent)) {
            recursive(parent);
        }

        final Map<Integer, Integer> parentMap = tree.get(parent);
        final Map<Integer, Integer> currentMap = copyMap(parentMap);
        final Item item = items[current];
        for (final Map.Entry<Integer, Integer> entry : parentMap.entrySet()) {
            if (entry.getKey() + item.weight > 100_000) {
                continue;
            }

            currentMap.compute(entry.getKey() + item.weight, (k, v) -> v == null ? entry.getValue() + item.value : Math.max(entry.getValue() + item.value, v));
        }

        tree.put(current, currentMap);
        return currentMap.entrySet().stream()
            .filter(e -> e.getKey() <= l)
            .mapToInt(Map.Entry::getValue)
            .max()
            .orElseThrow();
    }

    private static Map<Integer, Integer> copyMap(final Map<Integer, Integer> map) {
        final Map<Integer, Integer> copy = new HashMap<>();
        for (final Map.Entry<Integer, Integer> entry : map.entrySet()) {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    private static void recursive(final int current) {
        if (tree.containsKey(current)) {
            return;
        }

        final int parent = current / 2;
        if (!tree.containsKey(parent)) {
            recursive(parent);
        }

        final Map<Integer, Integer> parentMap = tree.get(parent);
        final Map<Integer, Integer> currentMap = copyMap(parentMap);
        final Item item = items[current];
        for (final Map.Entry<Integer, Integer> entry : parentMap.entrySet()) {
            if (entry.getKey() + item.weight > 100_000) {
                continue;
            }

            currentMap.compute(entry.getKey() + item.weight, (k, v) -> v == null ? entry.getValue() + item.value : Math.max(entry.getValue() + item.value, v));
        }

        tree.put(current, currentMap);
    }

    private static class Item {
        final int value;
        final int weight;

        Item(final int value, final int weight) {
            this.value = value;
            this.weight = weight;
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
