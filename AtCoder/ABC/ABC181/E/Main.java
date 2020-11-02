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

/*
前後から累積和
 */
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
            if (i > 0) {
                forward[i] += forward[i - 1];
            }
        }
        final long[] backward = new long[n / 2];
        for (int i = n / 2; i > 0; i--) {
            backward[i - 1] = children.get(2 * i) - children.get(2 * i - 1);
            if (i < n / 2) {
                backward[i - 1] += backward[i];
            }
        }

        long min = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long tmp = minBinarySearch(height, children.get(i));
            if (i % 2 == 0) {
                if (i > 0) {
                    tmp += forward[i / 2 - 1];
                }
                if (i < n - 1) {
                    tmp += backward[i / 2];
                }
                min = Math.min(min, tmp);
            } else {
                if (i > 1) {
                    tmp += forward[i / 2 - 1];
                }
                if (i < n - 2) {
                    tmp += backward[i / 2 + 1];
                }
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
    }
}
