package other.typecal90.U021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 強連結成分分解
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Pair> pairs = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .collect(Collectors.toList());
        final Map<Integer, List<Integer>> naturalMap = pairs.stream()
            .collect(Collectors.groupingBy(it -> it.from))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(it -> it.to)
                    .collect(Collectors.toList()))
            );

        final List<Integer> list = naturalDfsEntry(n, naturalMap);

        final Map<Integer, List<Integer>> reverseMap = pairs.stream()
            .collect(Collectors.groupingBy(it -> it.to))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(it -> it.from)
                    .collect(Collectors.toList()))
            );
        final boolean[] isVisited = new boolean[n + 1];
        final long answer = list.stream()
            .mapToLong(it -> reverseDfs(it, isVisited, reverseMap))
            .map(it -> it * (it - 1) / 2)
            .sum();
        System.out.println(answer);
    }

    private static long reverseDfs(final int current, final boolean[] isVisited, final Map<Integer, List<Integer>> map) {
        if (isVisited[current]) {
            return 0;
        }
        isVisited[current] = true;
        return get(map, current).stream()
            .mapToLong(it -> reverseDfs(it, isVisited, map))
            .sum() + 1;
    }

    private static List<Integer> naturalDfsEntry(final int n, final Map<Integer, List<Integer>> map) {
        final LinkedList<Integer> list = new LinkedList<>();
        final boolean[] isVisited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            naturalDfs(i, map, list, isVisited);
        }
        return list;
    }

    private static void naturalDfs(final int current, final Map<Integer, List<Integer>> map, final LinkedList<Integer> list, final boolean[] isVisited) {
        if (isVisited[current]) {
            return;
        }
        isVisited[current] = true;
        for (final int next : get(map, current)) {
            naturalDfs(next, map, list, isVisited);
        }
        list.addFirst(current);
    }

    private static List<Integer> get(final Map<Integer, List<Integer>> map, final int key) {
        return Optional.ofNullable(map.get(key)).orElse(Collections.emptyList());
    }

    private static class Pair {
        final int from;
        final int to;

        Pair(final int from, final int to) {
            this.from = from;
            this.to = to;
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
