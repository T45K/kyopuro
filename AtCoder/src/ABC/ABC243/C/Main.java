package ABC.ABC243.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
左に向かう点の左側に右に向かう点があれば良い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Pair<Integer, Integer>>> map = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            map.computeIfAbsent(y, k -> new ArrayList<>()).add(new Pair<>(x, i));
        }
        final char[] s = scanner.next().toCharArray();
        for (final List<Pair<Integer, Integer>> value : map.values()) {
            if (value.size() == 1) {
                continue;
            }
            final List<Integer> list = value.stream()
                .sorted(Comparator.comparingInt(pair -> pair.first))
                .map(pair -> pair.second)
                .collect(Collectors.toList());
            boolean existsTowardRight = false;
            for (final int tmp : list) {
                if (s[tmp] == 'R') {
                    existsTowardRight = true;
                }
                if (s[tmp] == 'L' && existsTowardRight) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
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

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
