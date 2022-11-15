package AtCoder.ABC.ABC274.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Pair<Integer, Integer>> positions = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(n + m)
            .collect(Collectors.toList());

        final double[][] dp = new double[1 << (n + m)][n + m];
        for (final double[] array : dp) {
            Arrays.fill(array, Double.MAX_VALUE);
        }
        for (int i = 0; i < n + m; i++) {
            dp[1 << i][i] = calcDistanceFromOrigin(positions.get(i));
        }
        for (int i = 0; i < 1 << (n + m); i++) {
            for (int j = 0; j < n + m; j++) {
                if (dp[i][j] == Double.MAX_VALUE) {
                    continue;
                }
                for (int l = 0; l < n + m; l++) {
                    final int dest = i | (1 << l);
                    if (dest == i) {
                        continue;
                    }
                    final int treasureCount = countTreasures(i, n, m);
                    dp[dest][l] = Math.min(
                        dp[dest][l],
                        dp[i][j] + calcDistance(positions.get(l), positions.get(j)) / Math.pow(2, treasureCount));
                }
            }
        }

        final int allCities = (1 << n) - 1;
        final double answer = IntStream.range((1 << n) - 1, 1 << (n + m))
            .filter(i -> (i & allCities) == allCities)
            .boxed()
            .flatMapToDouble(i -> IntStream.range(0, n + m)
                .filter(j -> dp[i][j] < Double.MAX_VALUE)
                .mapToDouble(j -> {
                    final double distance = calcDistanceFromOrigin(positions.get(j));
                    final int treasureCount = countTreasures(i, n, m);
                    return dp[i][j] + distance / Math.pow(2, treasureCount);
                })
            ).min()
            .orElseThrow();
        System.out.println(answer);
    }

    private static int countTreasures(final int route, final int n, final int m) {
        return (int) IntStream.range(n, n + m)
            .map(i -> route & (1 << i))
            .filter(and -> and > 0)
            .count();
    }

    private static double calcDistance(final Pair<Integer, Integer> a, final Pair<Integer, Integer> b) {
        return calcDistance(a.first, a.second, b.first, b.second);
    }

    private static double calcDistance(final long x1, final long y1, final long x2, final long y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    private static double calcDistanceFromOrigin(final Pair<Integer, Integer> a) {
        return calcDistance(a.first, a.second, 0, 0);
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
