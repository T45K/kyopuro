package ABC.ABC272.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    private static final int[] X_DIRECTION = {1, 1, -1, -1};
    private static final int[] Y_DIRECTION = {1, -1, 1, -1};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final long[] candidates = LongStream.rangeClosed(0, m)
            .map(l -> l * l)
            .toArray();
        final List<Move> moves = IntStream.rangeClosed(0, m)
            .mapToObj(l -> {
                final int index = Arrays.binarySearch(candidates, m - (long) l * l);
                if (index >= 0) {
                    return new Move(l, index);
                } else {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        final int[][] table = new int[n][n];
        for (final int[] array : table) {
            Arrays.fill(array, -1);
        }
        final PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        queue.add(new Point(0, 0, 0));
        while (!queue.isEmpty()) {
            final Point poll = queue.poll();
            if (table[poll.x][poll.y] != -1) {
                continue;
            }
            table[poll.x][poll.y] = poll.cost;
            for (int i = 0; i < 4; i++) {
                for (final Move move : moves) {
                    final int xNext = poll.x + move.x * X_DIRECTION[i];
                    final int yNext = poll.y + move.y * Y_DIRECTION[i];
                    if (0 <= xNext && xNext < n &&
                        0 <= yNext && yNext < n &&
                        table[xNext][yNext] == -1) {
                        queue.add(new Point(xNext, yNext, poll.cost + 1));
                    }
                }
            }
        }
        final String answer = Arrays.stream(table)
            .map(array -> Arrays.stream(array)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" ")))
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Point {
        final int x;
        final int y;
        final int cost;

        Point(final int x, final int y, final int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    private static class Move {
        final int x;
        final int y;

        Move(final int x, final int y) {
            this.x = x;
            this.y = y;
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
