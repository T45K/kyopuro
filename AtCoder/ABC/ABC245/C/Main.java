package AtCoder.ABC.ABC245.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[] a = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final int[] b = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final boolean[][] table = new boolean[n][2];
        table[0][0] = true;
        table[0][1] = true;
        for (int i = 0; i < n - 1; i++) {
            if (table[i][0]) {
                if (Math.abs(a[i] - a[i + 1]) <= k) {
                    table[i + 1][0] = true;
                }
                if (Math.abs(a[i] - b[i + 1]) <= k) {
                    table[i + 1][1] = true;
                }
            }
            if (table[i][1]) {
                if (Math.abs(b[i] - a[i + 1]) <= k) {
                    table[i + 1][0] = true;
                }
                if (Math.abs(b[i] - b[i + 1]) <= k) {
                    table[i + 1][1] = true;
                }
            }
        }
        if (table[n - 1][0] || table[n - 1][1]) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
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
