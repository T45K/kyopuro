package AtCoder.ARC.ARC120.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] a = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToLong(Integer::intValue)
            .toArray();
        final long[] b = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToLong(Integer::intValue)
            .toArray();
        if (Arrays.stream(a).sum() != Arrays.stream(b).sum()) {
            System.out.println(-1);
            return;
        }

        if (Arrays.equals(a, b)) {
            System.out.println(0);
            return;
        }

        long swap = 0;
        final Map<Long, Deque<Integer>> map = new HashMap<>();
        int index = 0;
        loop:
        for (int i = 0; i < n; i++) { // b
            final long bValue = b[i];
            if (map.containsKey(bValue + i) && !map.get(bValue + i).isEmpty()) {
                final Integer integer = map.get(bValue + i).pollFirst();
                swap += integer - (i - 1);
                continue;
            }
            while (index < n) {
                if (bValue == a[index] + index - i) {
                    swap += index - i;
                    continue loop;
                }
                map.computeIfAbsent(a[index] + index, k -> new ArrayDeque<>()).add(index);
                index++;
            }
            System.out.println(-1);
            return;
        }
        System.out.println(swap);
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
