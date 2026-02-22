package ABC.ABC260.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
V1からV2に伸ばしている辺の組み合わせは全探索できる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int s = scanner.nextInt();
        final int t = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Integer>> group = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .collect(Collectors.groupingBy(p -> p.first, LinkedHashMap::new, Collectors.mapping(p -> p.second, Collectors.toList())));

        final int[][] table = new int[t + 1][t + 1];
        final Set<Integer> keys = group.keySet();
        for (final int key : keys) {
            final List<Integer> list = group.get(key);
            if (list.size() == 1) {
                continue;
            }

            for (int i = 0; i < list.size(); i++) {
                final int a = list.get(i) - s;
                for (int j = i + 1; j < list.size(); j++) {
                    final int b = list.get(j) - s;
                    if (table[a][b] > 0) {
                        System.out.println(key + " " + (a + s) + " " + (b + s) + " " + table[a][b]);
                        return;
                    } else {
                        table[a][b] = key;
                        table[b][a] = key;
                    }
                }
            }
        }
        System.out.println(-1);
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
