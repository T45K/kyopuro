package AtCoder.ABC.ABC241.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int q = scanner.nextInt();
        final TreeMap<Long, Integer> counts = new TreeMap<>();
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int query = scanner.nextInt();
            final long x = scanner.nextLong();
            switch (query) {
                case 1: {
                    counts.compute(x, (k, v) -> v == null ? 1 : v + 1);
                    break;
                }
                case 2: {
                    final int k = scanner.nextInt();
                    final long answer = recursiveFloor(x, k, counts);
                    joiner.add(Long.toString(answer));
                    break;
                }
                case 3: {
                    final int k = scanner.nextInt();
                    final long answer = recursiveCeiling(x, k, counts);
                    joiner.add(Long.toString(answer));
                    break;
                }
            }
        }
        System.out.println(joiner);
    }

    private static long recursiveFloor(final long x, final int k, final TreeMap<Long, Integer> counts) {
        final Map.Entry<Long, Integer> entry = counts.floorEntry(x);
        if (entry == null) {
            return -1;
        }
        if (entry.getValue() >= k) {
            return entry.getKey();
        }
        return recursiveFloor(entry.getKey() - 1, k - entry.getValue(), counts);
    }

    private static long recursiveCeiling(final long x, final int k, final TreeMap<Long, Integer> counts) {
        final Map.Entry<Long, Integer> entry = counts.ceilingEntry(x);
        if (entry == null) {
            return -1;
        }
        if (entry.getValue() >= k) {
            return entry.getKey();
        }
        return recursiveCeiling(entry.getKey() + 1, k - entry.getValue(), counts);
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
