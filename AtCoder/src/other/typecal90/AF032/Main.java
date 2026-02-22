package other.typecal90.AF032;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[][] table = Stream.generate(() -> Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray()
        )
            .limit(n)
            .toArray(int[][]::new);

        final int m = scanner.nextInt();
        final Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            map.computeIfAbsent(x, k -> new HashSet<>()).add(y);
            map.computeIfAbsent(y, k -> new HashSet<>()).add(x);
        }

        final int[] array = new int[n];
        final int answer = permutation(array, 0, table, map, IntStream.rangeClosed(1, n).boxed().collect(Collectors.toCollection(ArrayDeque::new)));
        System.out.println(answer < Integer.MAX_VALUE ? answer : -1);
    }

    private static int permutation(final int[] array, final int index, final int[][] table, final Map<Integer, Set<Integer>> map, final Deque<Integer> queue) {
        if (array.length == index) {
            for (int i = 0; i < array.length - 1; i++) {
                if (get(map, array[i]).contains(array[i + 1])) {
                    return Integer.MAX_VALUE;
                }
            }
            return IntStream.range(0, array.length)
                .map(i -> table[array[i] - 1][i])
                .sum();
        }

        int min = Integer.MAX_VALUE;
        final int size = queue.size();
        for (int i = 0; i < size; i++) {
            final int value = Optional.ofNullable(queue.pollFirst()).orElseThrow();
            array[index] = value;
            min = Math.min(min, permutation(array, index + 1, table, map, queue));
            queue.addLast(value);
        }
        return min;
    }

    private static Set<Integer> get(final Map<Integer, Set<Integer>> map, final int key) {
        return Optional.ofNullable(map.get(key)).orElse(Collections.emptySet());
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
