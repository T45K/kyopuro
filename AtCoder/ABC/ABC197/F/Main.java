package AtCoder.ABC.ABC197.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
解説AC
元のグラフから新しいグラフを作る
(スタートから移動した場所, ゴールから移動した場所)が頂点
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        final BiFunction<Integer, Integer, Integer> toPair = (a, b) -> a * (n + 1) + b;
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.next().toCharArray()[0] - 'a';
            graph.computeIfAbsent(c, v -> new ArrayList<>()).add(toPair.apply(a, b));
        }
        final Map<Integer, List<Integer>> newGraph = new HashMap<>();
        for (final List<Integer> list : graph.values()) {
            for (final int pari1 : list) {
                final int a = pari1 / (n + 1);
                final int b = pari1 % (n + 1);
                for (final int pair2 : list) {
                    final int c = pair2 / (n + 1);
                    final int d = pair2 % (n + 1);
                    newGraph.computeIfAbsent(toPair.apply(a, c), v -> new ArrayList<>()).add(toPair.apply(b, d));
                    newGraph.computeIfAbsent(toPair.apply(a, d), v -> new ArrayList<>()).add(toPair.apply(b, c));
                    newGraph.computeIfAbsent(toPair.apply(b, c), v -> new ArrayList<>()).add(toPair.apply(a, d));
                    newGraph.computeIfAbsent(toPair.apply(b, d), v -> new ArrayList<>()).add(toPair.apply(a, c));
                }
            }
        }

        if (!newGraph.containsKey(toPair.apply(1, n))) {
            System.out.println(-1);
            return;
        }

        final Set<Integer> edges = graph.values().stream()
            .flatMap(Collection::stream)
            .flatMap(pair ->
                Stream.of(toPair.apply(pair / (n + 1), pair % (n + 1)),
                    toPair.apply(pair % (n + 1), pair / (n + 1))))
            .collect(Collectors.toSet());
        final int even = bfs(newGraph, n, toPair, Integer::equals);
        final int odd = bfs(newGraph, n, toPair, (a, b) -> edges.contains(toPair.apply(a, b)));
        if (even == -1 && odd == -1) {
            System.out.println(-1);
        } else if (odd == -1) {
            System.out.println(even * 2);
        } else if (even == -1) {
            System.out.println(odd * 2 + 1);
        } else {
            System.out.println(Math.min(even * 2, odd * 2 + 1));
        }
    }

    private static int bfs(final Map<Integer, List<Integer>> graph,
                           final int n,
                           final BiFunction<Integer, Integer, Integer> toPair,
                           final BiFunction<Integer, Integer, Boolean> criterion) {
        final Set<Integer> isVisited = new HashSet<>();
        final Deque<QueueElement> queue = new ArrayDeque<>();
        if (criterion.apply(1, n)) {
            return 0;
        }
        for (final int pair : graph.get(toPair.apply(1, n))) {
            if (isVisited.contains(pair)) {
                continue;
            }
            isVisited.add(pair);
            queue.add(new QueueElement(pair, 1));
        }
        while (!queue.isEmpty()) {
            final QueueElement top = queue.pollFirst();
            final int a = top.pair / (n + 1);
            final int b = top.pair % (n + 1);
            if (criterion.apply(a, b)) {
                return top.count;
            }
            for (final int pair : graph.get(top.pair)) {
                if (isVisited.contains(pair)) {
                    continue;
                }
                isVisited.add(pair);
                queue.add(new QueueElement(pair, top.count + 1));
            }
        }
        return -1;
    }

    private static class QueueElement {
        final int pair;
        final int count;

        QueueElement(final int pair, final int count) {
            this.pair = pair;
            this.count = count;
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
    