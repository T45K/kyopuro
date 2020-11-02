package AtCoder.ABC.ABC181.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Long> children = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .sorted()
            .collect(Collectors.toList());
        final List<Long> height = IntStream.range(0, m)
            .mapToObj(i -> scanner.nextLong())
            .sorted()
            .collect(Collectors.toList());

        if (n == 1) {
            System.out.println(minBinarySearch(height, children.get(0)));
            return;
        }

        final long[] forward = new long[n / 2];
        for (int i = 0; i < n / 2; i++) {
            forward[i] = children.get(2 * i + 1) - children.get(2 * i);
        }
        for (int i = 0; i < n / 2 - 1; i++) {
            forward[i + 1] += forward[i];
        }
        final long[] backward = new long[n / 2];
        for (int i = n / 2; i > 0; i--) {
            backward[i - 1] = children.get(2 * i) - children.get(2 * i - 1);
        }
        for (int i = n / 2 - 2; i >= 0; i--) {
            backward[i] += backward[i + 1];
        }

        long min = minBinarySearch(height, children.get(0)) + backward[0];
        for (int i = 1; i < n; i++) {
            long tmp = 0;
            if (i % 2 == 0) {
                if (i < n - 1) {
                    tmp += backward[i / 2];
                }
                min = Math.min(min, tmp + forward[i / 2 - 1] + minBinarySearch(height, children.get(i)));
            } else {
                if (i > 1) {
                    tmp += forward[i / 2 - 1];
                }
                if (i < n - 2) {
                    tmp += backward[i / 2 + 1];
                }
                tmp += minBinarySearch(height, children.get(i));
                min = Math.min(min, tmp + children.get(i + 1) - children.get(i - 1));
            }
        }
        System.out.println(min);
    }

    private static long minBinarySearch(final List<Long> list, final long target) {
        final int idx = Collections.binarySearch(list, target);
        if (idx >= 0) {
            return 0;
        }

        final int insertPoint = ~idx;
        if (insertPoint == 0) {
            return list.get(0) - target;
        }
        if (insertPoint == list.size()) {
            return target - list.get(list.size() - 1);
        }
        return Math.min(target - list.get(insertPoint - 1), list.get(insertPoint) - target);
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
