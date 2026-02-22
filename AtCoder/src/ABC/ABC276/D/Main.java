package ABC.ABC276.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        final List<Map<Integer, Integer>> decomposed = list.stream()
            .map(Main::decompose)
            .collect(Collectors.toList());
        final Map<Integer, Integer> criteria = decomposed.get(0);
        final int min = criteria.keySet().stream()
            .map(key -> {
                final boolean isContained = decomposed.stream().allMatch(map -> map.containsKey(key));
                if (!isContained) {
                    return null;
                }
                return decomposed.stream()
                    .mapToInt(map -> map.get(key))
                    .sum();
            })
            .filter(Objects::nonNull)
            .min(Comparator.naturalOrder())
            .orElse(-1);

        System.out.println(min);
    }

    private static Map<Integer, Integer> decompose(final int v) {
        final Map<Integer, Integer> map = new HashMap<>();
        map.put(v, 0);
        final Deque<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            final int head = queue.pollFirst();
            final int count = map.get(head);
            if (head % 2 == 0 && !map.containsKey(head / 2) && head / 2 > 0) {
                map.put(head / 2, count + 1);
                queue.addLast(head / 2);
            }
            if (head % 3 == 0 && !map.containsKey(head / 3) && head / 3 > 0) {
                map.put(head / 3, count + 1);
                queue.addLast(head / 3);
            }
        }
        return map;
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
