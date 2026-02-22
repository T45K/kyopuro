package ABC.ABC243.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final long[][] table = new long[n][n];
        for (final long[] array : table) {
            Arrays.fill(array, Long.MAX_VALUE / 2);
        }
        final List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt() - 1;
            final int b = scanner.nextInt() - 1;
            final int c = scanner.nextInt();
            table[a][b] = c;
            table[b][a] = c;
            edges.add(new Edge(a, b, c));
        }
        final Set<Pair<Integer, Integer>> same = new HashSet<>();
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (table[i][j] == table[i][k] + table[k][j]) {
                        same.add(new Pair<>(Math.min(i, j), Math.max(i, j)));
                    }
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }
        final long answer = edges.stream()
            .filter(edge -> edge.c > table[edge.a][edge.b] || same.contains(new Pair<>(Math.min(edge.a, edge.b), Math.max(edge.a, edge.b))))
            .count();
        System.out.println(answer);
    }

    private static class Edge {
        final int a;
        final int b;
        final long c;

        Edge(final int a, final int b, final long c) {
            this.a = a;
            this.b = b;
            this.c = c;
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
