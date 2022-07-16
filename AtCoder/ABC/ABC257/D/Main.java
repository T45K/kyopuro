package AtCoder.ABC.ABC257.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<JumpStand> list = IntStream.range(0, n)
            .mapToObj(i -> new JumpStand(i, scanner.nextLong(), scanner.nextLong(), scanner.nextLong()))
            .collect(Collectors.toList());

        final List<List<Route>> routes = list.stream()
            .map(from -> list.stream()
                .filter(to -> from.id != to.id)
                .map(to -> new Route(from, to))
                .collect(Collectors.toList()))
            .collect(Collectors.toList());

        final long answer = IntStream.range(0, n)
            .mapToLong(i -> calc(i, routes, n))
            .min()
            .orElseThrow();
        System.out.println(answer);
    }

    private static long calc(final int start, final List<List<Route>> routes, final int n) {
        final Set<Integer> visitedJumpStands = new HashSet<>();
        visitedJumpStands.add(start);

        final PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparing(Route::calcRequiredTrainingCounts));
        queue.addAll(routes.get(start));
        assert !queue.isEmpty();
        long requiredTrainingCounts = queue.peek().calcRequiredTrainingCounts();
        while (!queue.isEmpty() && visitedJumpStands.size() < n) {
            final Route poll = queue.poll();
            requiredTrainingCounts = Math.max(requiredTrainingCounts, poll.calcRequiredTrainingCounts());
            if (visitedJumpStands.contains(poll.to.id)) {
                continue;
            }

            visitedJumpStands.add(poll.to.id);
            for (final Route next : routes.get(poll.to.id)) {
                if (!visitedJumpStands.contains(next.to.id)) {
                    queue.add(next);
                }
            }
        }

        return requiredTrainingCounts;
    }

    private static class Route {
        final JumpStand from;
        final JumpStand to;

        Route(final JumpStand from, final JumpStand to) {
            this.from = from;
            this.to = to;
        }

        long calcRequiredTrainingCounts() {
            return (Math.abs(this.to.x - this.from.x) + Math.abs(this.to.y - this.from.y) + this.from.p - 1) / this.from.p;
        }
    }

    private static class JumpStand {
        final int id;
        final long x;
        final long y;
        final long p;

        JumpStand(final int id, final long x, final long y, final long p) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.p = p;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
