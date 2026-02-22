package ABC.ABC204.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            map.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }
        System.out.println(IntStream.rangeClosed(1, n)
            .mapToLong(i -> dfs(map, n, i))
            .sum());
    }

    private static long dfs(final Map<Integer, List<Integer>> map, final int n, final int start) {
        final boolean[] array = new boolean[n + 1];
        array[start] = true;
        final Consumer<Integer> consumer = new Consumer<>() {
            @Override
            public void accept(final Integer start) {
                for (final int next : get(map, start)) {
                    if (array[next]) {
                        continue;
                    }
                    array[next] = true;
                    accept(next);
                }
            }
        };
        consumer.accept(start);
        return IntStream.rangeClosed(1, n)
            .filter(i -> array[i])
            .count();
    }

    private static List<Integer> get(final Map<Integer, List<Integer>> map, final int key) {
        return Optional.ofNullable(map.get(key)).orElse(Collections.emptyList());
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
