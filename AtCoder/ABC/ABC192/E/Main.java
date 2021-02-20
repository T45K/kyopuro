package AtCoder.ABC.ABC192.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
ダイクストラ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int x = scanner.nextInt() - 1;
        final int y = scanner.nextInt() - 1;
        final Map<Integer, List<Triple>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;
            final int t = scanner.nextInt();
            final int k = scanner.nextInt();
            map.computeIfAbsent(a, v -> new ArrayList<>()).add(new Triple(b, t, k));
            map.computeIfAbsent(b, v -> new ArrayList<>()).add(new Triple(a, t, k));
        }

        final long[] times = new long[n];
        Arrays.fill(times, Long.MAX_VALUE);
        times[x] = 0;
        final PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingLong(pair -> times[pair.a]));
        queue.add(new Pair(x, 0));
        while (!queue.isEmpty()) {
            final Pair current = queue.poll();
            final int currentStation = current.a;
            final long currentTime = current.t;
            for (final Triple triple : Optional.ofNullable(map.get(currentStation)).orElse(Collections.emptyList())) {
                final int b = triple.b;
                final long k = triple.k;
                final long t = triple.t;
                final long time = (currentTime + k - 1) / k * k + t;
                if (time < times[triple.b]) {
                    times[b] = time;
                    queue.add(new Pair(b, time));
                }
            }
        }

        if (times[y] < Long.MAX_VALUE) {
            System.out.println(times[y]);
        } else {
            System.out.println(-1);
        }
    }

    private static class Pair {
        final int a;
        final long t;

        Pair(final int a, final long t) {
            this.a = a;
            this.t = t;
        }
    }

    private static class Triple {
        final int b;
        final long t;
        final long k;

        Triple(final int b, final int t, final int k) {
            this.b = b;
            this.t = t;
            this.k = k;
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
