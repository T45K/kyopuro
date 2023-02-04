package AtCoder.ABC.ABC288.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO: solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final long[] original = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            original[i] = scanner.nextInt();
        }
        final int q = scanner.nextInt();
        final List<Query> queries = IntStream.range(0, q)
            .mapToObj(i -> new Query(i, scanner.nextInt(), scanner.nextInt()))
            .sorted(Comparator.comparingInt(query -> query.r))
            .collect(Collectors.toList());

        final long[] acc = new long[n + 1];
        int index = 1;
        final TreeMap<Integer, String> answers = new TreeMap<>();
        for (final Query query : queries) {
            for (int i = index; i <= query.r - k + 1; i++) {
                final long diff = -(original[i] + acc[i - 1]);
                for (int j = 1; j < k; j++) {
                    acc[i + j] += diff;
                }
            }
            index = query.r - k + 1;
            if (-acc[query.l] - acc[query.r] + original[query.r] == 0) {
                answers.put(query.id, "Yes");
            } else {
                answers.put(query.id, "No");
            }
        }

        final String answer = String.join("\n", answers.values());
        System.out.println(answer);
    }

    private static class Query {
        final int id;
        final int l;
        final int r;

        Query(final int id, final int l, final int r) {
            this.id = id;
            this.l = l;
            this.r = r;
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
