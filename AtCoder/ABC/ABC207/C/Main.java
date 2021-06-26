package AtCoder.ABC.ABC207.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Range> list = Stream.generate(() -> {
            final int t = scanner.nextInt();
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            switch (t) {
                case 1:
                    return new Range(l, r);
                case 2:
                    return new Range(l, r - 0.1);
                case 3:
                    return new Range(l + 0.1, r);
                case 4:
                    return new Range(l + 0.1, r - 0.1);
            }
            throw new RuntimeException();
        })
            .limit(n)
            .collect(Collectors.toList());
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            final Range range1 = list.get(i);
            for (int j = i + 1; j < n; j++) {
                final Range range2 = list.get(j);
                if (Math.min(range1.r, range2.r) >= Math.max(range1.l, range2.l)) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }

    private static class Range {
        final double l;
        final double r;

        Range(final double l, final double r) {
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
}
