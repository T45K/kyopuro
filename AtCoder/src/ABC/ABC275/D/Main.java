package ABC.ABC275.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final TreeMap<Long, Long> map = new TreeMap<>();
        final PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.add(n);
        while (!queue.isEmpty()) {
            final long head = queue.poll();
            if (map.containsKey(head)) {
                continue;
            }
            map.put(head, 0L);
            if (!map.containsKey(head / 2) && head / 2 >= 1) {
                queue.add(head / 2);
            }
            if (!map.containsKey(head / 3) && head / 3 >= 1) {
                queue.add(head / 3);
            }
        }

        map.put(0L, 1L);
        for (final long key : map.keySet()) {
            if (key == 0) {
                continue;
            }

            map.put(key, map.get(key / 2) + map.get(key / 3));
        }

        System.out.println(map.get(n));
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
