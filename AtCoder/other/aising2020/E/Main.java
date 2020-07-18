package AtCoder.other.aising2020.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        IntStream.range(0, t)
            .map(i -> scanner.nextInt())
            .mapToObj(n -> IntStream.range(0, n)
                .mapToObj(i -> new Camel(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
                .collect(Collectors.toList()))
            .map(list -> {
                long sum = list.stream()
                    .mapToLong(camel -> min(camel.l, camel.r))
                    .sum();

                final Map<Integer, List<Camel>> lCamels = list.stream()
                    .filter(camel -> camel.l >= camel.r)
                    .collect(Collectors.groupingBy(camel -> camel.index));

                final Map<Integer, List<Camel>> rCamels = list.stream()
                    .filter(camel -> camel.r > camel.l)
                    .collect(Collectors.groupingBy(camel -> list.size() - camel.index));

                final PriorityQueue<Camel> lQueue = new PriorityQueue<>(Comparator.comparingLong(camel -> camel.l - camel.r));
                final PriorityQueue<Camel> rQueue = new PriorityQueue<>(Comparator.comparingLong(camel -> camel.r - camel.l));
                for (int j = 1; j <= list.size(); j++) {
                    if (lCamels.containsKey(j)) {
                        lQueue.addAll(lCamels.get(j));
                        while (lQueue.size() > j) {
                            lQueue.poll();
                        }
                    }

                    if (rCamels.containsKey(j)) {
                        rQueue.addAll(rCamels.get(j));
                        while (rQueue.size() > j) {
                            rQueue.poll();
                        }
                    }
                }

                return sum
                    + lQueue.stream().mapToLong(camel -> camel.l - camel.r).sum()
                    + rQueue.stream().mapToLong(camel -> camel.r - camel.l).sum();
            })
            .forEach(System.out::println);
    }

    private static class Camel {
        final int index;
        final long l;
        final long r;

        Camel(final int index, final long l, final long r) {
            this.index = index;
            this.l = l;
            this.r = r;
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
