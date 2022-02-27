package AtCoder.ABC.ABC240.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int[] a = new int[n];
        final int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
        }
        final Map<Integer, Set<Integer>> counts = new HashMap<>();
        final TriConsumer<int[], Integer, Integer> incrementCount = (array, base, count) ->
            counts.computeIfAbsent(base + array[count], k -> new LinkedHashSet<>()).add(count + 1);
        incrementCount.accept(a, 0, 0);
        incrementCount.accept(b, 0, 0);
        for (int i = 1; i < x; i++) {
            final Set<Integer> values = counts.get(i);
            if (values == null) {
                continue;
            }
            for (final int count : values) {
                if (count == n) {
                    continue;
                }
                if (i + a[count] <= x) {
                    incrementCount.accept(a, i, count);
                }
                if (i + b[count] <= x) {
                    incrementCount.accept(b, i, count);
                }
            }
        }
        if (counts.getOrDefault(x, Collections.emptySet()).contains(n)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private interface TriConsumer<T1, T2, T3> {
        void accept(final T1 t1, final T2 t2, final T3 t3);
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
