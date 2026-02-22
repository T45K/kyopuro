package other.indeednow_2015_qual.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int c = scanner.nextInt();
        final Map<Integer, List<Integer>> counts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            counts.computeIfAbsent(a, Main::createList).add(i + 1);
        }

        final long all = (long) n * ((long) n + 1) / 2;
        IntStream.rangeClosed(1, c)
            .mapToLong(i -> {
                final List<Integer> list = counts.get(i);
                return list == null ? 0 :
                    all - IntStream.range(0, list.size() - 1)
                        .mapToLong(j -> list.get(j + 1) - list.get(j) - 1)
                        .map(v -> v * (v + 1) / 2)
                        .sum();
            }).forEach(System.out::println);

    }

    private static List<Integer> createList(final int v) {
        final List<Integer> list = new ArrayList<>();
        list.add(0);
        return list;
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
