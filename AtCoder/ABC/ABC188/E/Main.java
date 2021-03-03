package AtCoder.ABC.ABC188.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }
        final Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            map.computeIfAbsent(x, v -> new ArrayList<>()).add(y);
        }
        final int[] minPrice = new int[n + 1];
        Arrays.fill(minPrice, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            final int min = Math.min(minPrice[i], array[i]);
            for (final int next : Optional.ofNullable(map.get(i)).orElse(Collections.emptyList())) {
                minPrice[next] = Math.min(minPrice[next], min);
            }
        }
        IntStream.rangeClosed(1, n)
            .filter(i -> minPrice[i] < Integer.MAX_VALUE)
            .map(i -> array[i] - minPrice[i])
            .max()
            .ifPresent(System.out::println);
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
