package AtCoder.ABC.ABC276.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class Main {
    private static final int[] X_MOVE = {-1, 0, 1, 0};
    private static final int[] Y_MOVE = {0, -1, 0, 1};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final char[][] table = new char[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            table[i] = s.toCharArray();
        }

        final Pair<Integer, Integer> start = IntStream.range(0, h)
            .boxed()
            .flatMap(i -> IntStream.range(0, w).mapToObj(j -> new Pair<>(i, j)))
            .filter(pair -> table[pair.first][pair.second] == 'S')
            .findAny()
            .orElseThrow();
        final int startX = start.first;
        final int startY = start.second;

        final BiFunction<Integer, Integer, Boolean> isInside = (x, y) -> x >= 0 && x < h && y >= 0 && y < w;

        for (int i = 0; i < 4; i++) {
            final int baseX = X_MOVE[i];
            final int baseY = Y_MOVE[i];
            if (!isInside.apply(startX + baseX, startY + baseY) || table[startX + baseX][startY + baseY] == '#') {
                continue;
            }
            final int[][] routes = new int[h][w];
            routes[startX][startY] = 1;
            routes[startX + baseX][startY + baseY] = 1;
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    continue;
                }
                if (isInside.apply(startX + X_MOVE[j], startY + Y_MOVE[j])) {
                    routes[startX + X_MOVE[j]][startY + Y_MOVE[j]] = Integer.MAX_VALUE;
                }
            }

            final Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
            queue.addLast(new Pair<>(startX + baseX, startY + baseY));
            while (!queue.isEmpty()) {
                final Pair<Integer, Integer> poll = queue.pollFirst();
                final int x = poll.first;
                final int y = poll.second;
                for (int j = 0; j < 4; j++) {
                    final int nextX = x + X_MOVE[j];
                    final int nextY = y + Y_MOVE[j];
                    if (!isInside.apply(nextX, nextY) || table[nextX][nextY] == '#') {
                        continue;
                    }
                    if (routes[nextX][nextY] == Integer.MAX_VALUE) {
                        System.out.println("Yes");
                        return;
                    }
                    if (routes[nextX][nextY] == 0) {
                        routes[nextX][nextY] = 1;
                        queue.addLast(new Pair<>(nextX, nextY));
                    }
                }
            }
        }
        System.out.println("No");
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
