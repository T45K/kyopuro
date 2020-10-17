package AtCoder.ABC.ABC180.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
巡回セールスマン問題
第三回PAST参照

初めにダイクストラで全ての街の移動コストを計算する
次に，DPで移動方法を計算する（DP[2^N][N]）
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<City> cities = IntStream.range(0, n)
            .mapToObj(i -> new City(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());

        final long[][] distances = new long[n][n];
        for (int i = 0; i < n; i++) {
            distances[i] = dijkstra(cities, i);
        }

        final int bit = 1 << n;
        final long[][] dp = new long[bit][n];
        for (final long[] array : dp) {
            Arrays.fill(array, Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 0; i < bit; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == Long.MAX_VALUE) {
                    continue;
                }

                final long current = dp[i][j];
                for (int k = 0; k < n; k++) {
                    if (j == k || (1 << k & i) > 0) {
                        continue;
                    }

                    final long value = current + distances[j][k];
                    final int dest = i + (1 << k);
                    dp[dest][k] = Math.min(dp[dest][k], value);
                }
            }
        }

        final long answer = Math.min(dp[bit - 1][0], IntStream.range(1, n)
            .filter(i -> dp[bit - 1][i] < Long.MAX_VALUE)
            .mapToLong(i -> dp[bit - 1][i] + distances[i][0])
            .min()
            .orElse(Long.MAX_VALUE));
        System.out.println(answer);
    }

    private static long[] dijkstra(final List<City> cities, final int start) {
        final long[] distances = new long[cities.size()];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;
        final PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparingLong(r -> r.cost));
        IntStream.range(0, cities.size())
            .filter(i -> i != start)
            .mapToObj(i -> new Route(i, move(cities.get(start), cities.get(i))))
            .forEach(queue::add);

        while (!queue.isEmpty()) {
            final Route poll = queue.poll();
            if (poll.cost >= distances[poll.to]) {
                continue;
            }

            distances[poll.to] = poll.cost;
            IntStream.range(0, cities.size())
                .filter(i -> distances[i] > poll.cost + move(cities.get(poll.to), cities.get(i)))
                .mapToObj(i -> new Route(i, poll.cost + move(cities.get(poll.to), cities.get(i))))
                .forEach(queue::add);
        }

        return distances;
    }

    private static long move(final City from, final City dest) {
        return Math.abs(dest.x - from.x) + Math.abs(dest.y - from.y) + Math.max(0, dest.z - from.z);
    }

    private static class Route {
        final int to;
        final long cost;

        Route(final int to, final long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class City {
        final long x;
        final long y;
        final long z;

        City(final int x, final int y, final int z) {
            this.x = x;
            this.y = y;
            this.z = z;
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
