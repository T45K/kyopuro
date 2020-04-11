package AtCoder.ABC.ABC144.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();
        final Long[] eat = new Long[n];
        final Long[] food = new Long[n];

        long all = 0;
        for (int i = 0; i < n; i++) {
            final long tmp = scanner.nextLong();
            eat[i] = tmp;
            all += tmp;
        }

        if (all <= k) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < n; i++) {
            food[i] = scanner.nextLong();
        }

        Arrays.sort(eat);
        Arrays.sort(food, Comparator.reverseOrder());

        final long max = (long) Math.pow(10, 12);
        final long answer = specialBinarySearch(0, max, k, eat, food);
        System.out.println(answer);
    }

    private static long specialBinarySearch(final long min, final long max, final long base, final Long[] eat, final Long[] food) {
        if (max - min <= 1) {
            return max;
        }

        final long middle = (min + max) / 2;
        long counter = 0;
        for (int i = 0; i < eat.length; i++) {
            final long cost = eat[i] * food[i];
            if (cost > middle) {
                counter += (cost - middle + food[i] - 1) / food[i];
            }
        }

        if (counter > base) {
            return specialBinarySearch(middle, max, base, eat, food);
        } else {
            return specialBinarySearch(min, middle, base, eat, food);
        }
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
