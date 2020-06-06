package AtCoder.other.past_3.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
子供の食べた寿司の最大値は降順になるのでにぶたん
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, m)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());

        final int[] array = new int[n];
        for (final int value : list) {
            if (array[0] < value) {
                array[0] = value;
                System.out.println(1);
                continue;
            } else if (array[n - 1] >= value) {
                System.out.println(-1);
                continue;
            }

            final int index = binarySearch(value, array, 0, n);
            System.out.println(index + 1);
            array[index] = value;
        }
    }

    private static int binarySearch(final int value, final int[] array, final int left, final int right) {
        if (right - left <= 1) {
            return right;
        }

        final int mid = (left + right) / 2;
        if (array[mid] >= value) {
            return binarySearch(value, array, mid, right);
        } else {
            return binarySearch(value, array, left, mid);
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
