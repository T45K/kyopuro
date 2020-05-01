package AtCoder.other.soundhound2018_summer_qual.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
グラフ 始点と終点からダイクストラする
なるべくqueueに値を追加しない方が良いので，addAllを使わずに一つずつ判定してaddした方が速い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int s = scanner.nextInt();
        final int t = scanner.nextInt();

        final Map<Integer, List<Destination>> yenGraph = new HashMap<>();
        final Map<Integer, List<Destination>> sunukeGraph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            yenGraph.computeIfAbsent(u, list -> new ArrayList<>()).add(new Destination(v, a));
            yenGraph.computeIfAbsent(v, list -> new ArrayList<>()).add(new Destination(u, a));
            sunukeGraph.computeIfAbsent(u, list -> new ArrayList<>()).add(new Destination(v, b));
            sunukeGraph.computeIfAbsent(v, list -> new ArrayList<>()).add(new Destination(u, b));
        }

        final long[] yenRoutes = dijkstra(yenGraph, s, n);
        final long[] sunukeRoutes = dijkstra(sunukeGraph, t, n);

        final long base = 1_000_000_000_000_000L;
        final long[] results = new long[n + 1];
        results[n] = base - yenRoutes[n] - sunukeRoutes[n];
        for (int i = n - 1; i >= 0; i--) {
            results[i] = Math.max(results[i + 1], base - yenRoutes[i] - sunukeRoutes[i]);
        }

        Arrays.stream(results, 1, n + 1)
                .forEach(System.out::println);
    }

    private static long[] dijkstra(final Map<Integer, List<Destination>> graph, final int start, final int n) {
        final long[] array = new long[n + 1];
        Arrays.fill(array, Long.MAX_VALUE);
        array[start] = 0;
        final PriorityQueue<Destination> queue = new PriorityQueue<>(Comparator.comparingLong(dest -> dest.cost));
        queue.addAll(graph.get(start));
        while (!queue.isEmpty()) {
            final Destination dest = queue.poll();
            if (dest.cost >= array[dest.to]) {
                continue;
            }
            array[dest.to] = dest.cost;
            graph.get(dest.to).stream()
                    .filter(next -> array[next.to] > next.cost + dest.cost)
                    .map(next -> new Destination(next.to, next.cost + dest.cost))
                    .forEach(queue::add);
        }
        return array;
    }

    private static class Destination {
        final int to;
        final long cost;

        Destination(final int to, final long cost) {
            this.to = to;
            this.cost = cost;
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
    