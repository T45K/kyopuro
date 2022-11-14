package AtCoder.ABC.ABC277.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();
        @SuppressWarnings("unchecked") final Map<Integer, Set<Integer>>[] routes = new HashMap[2];
        routes[0] = new HashMap<>();
        routes[1] = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            final int a = scanner.nextInt();
            routes[a].computeIfAbsent(u, key -> new LinkedHashSet<>()).add(v);
            routes[a].computeIfAbsent(v, key -> new LinkedHashSet<>()).add(u);
        }

        final Set<Integer> switches = Stream.generate(scanner::nextInt)
            .limit(k)
            .collect(Collectors.toSet());

        final int[][] costs = new int[2][n + 1];
        Arrays.fill(costs[0], Integer.MAX_VALUE);
        Arrays.fill(costs[1], Integer.MAX_VALUE);
        @SuppressWarnings("unchecked") final Deque<Route>[] queues = new ArrayDeque[2];
        queues[0] = new ArrayDeque<>();
        queues[1] = new ArrayDeque<>();
        queues[1].add(new Route(1, 0));
        int currentStatus = 1;
        while (!(queues[0].isEmpty() && queues[1].isEmpty())) {
            final Deque<Route> queue = queues[currentStatus];
            final int[] cost = costs[currentStatus];
            final int nextStatus = 1 - currentStatus;
            while (!queue.isEmpty()) {
                final Route poll = queue.poll();
                final int currentPosition = poll.dest;
                final int currentCost = poll.cost;
                if (cost[currentPosition] <= currentCost) {
                    continue;
                }
                cost[currentPosition] = currentCost;
                if (switches.contains(currentPosition) && costs[nextStatus][currentPosition] > currentCost) {
                    queues[nextStatus].add(new Route(currentPosition, currentCost));
                }
                for (final int next : routes[currentStatus].getOrDefault(currentPosition, Collections.emptySet())) {
                    if (cost[next] > currentCost + 1) {
                        queue.add(new Route(next, currentCost + 1));
                    }
                }
            }
            currentStatus = nextStatus;
        }

        if (Math.min(costs[0][n], costs[1][n]) == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(costs[0][n], costs[1][n]));
        }
    }

    private static class Route {
        final int dest;
        final int cost;

        Route(final int dest, final int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
