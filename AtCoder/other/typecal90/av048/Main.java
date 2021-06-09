package AtCoder.other.typecal90.av048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Problem singleton = new Problem(-1, -1);

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final Deque<Problem> problems = Stream.generate(() -> new Problem(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .sorted(Comparator.comparingLong(Problem::getPart).reversed())
            .collect(Collectors.toCollection(ArrayDeque::new));
        final PriorityQueue<Long> fullQueue = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0;
        for (int i = 0; i < k; i++) {
            final Problem problem = problems.isEmpty() ? singleton : problems.peek();
            final long full = fullQueue.isEmpty() ? -1 : fullQueue.peek();
            if (problem.getPart() >= full) {
                problems.poll();
                sum += problem.getPart();
                fullQueue.add(problem.getFull() - problem.getPart());
            } else {
                fullQueue.poll();
                sum += full;
            }
        }
        System.out.println(sum);
    }

    private static class Problem {
        private final long full;
        private final long part;

        Problem(final long full, final long part) {
            this.full = full;
            this.part = part;
        }

        long getFull() {
            return full;
        }

        long getPart() {
            return part;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
