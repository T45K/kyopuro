package ABC095.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// TODO fix
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long c = scanner.nextLong();

        int centerIndex = 0;
        final Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            final long length = scanner.nextLong();
            final long value = scanner.nextLong();

            if (centerIndex == 0 && length * 2 > c) {
                centerIndex = i;
            }

            pairs[i] = new Pair(length, value);
        }

        long result = 0;
        {
            long temp = 0;
            for (final Pair pair : pairs) {
                temp += pair.value;
                result = Math.max(result, temp - pair.length);
            }
        }
        {
            long temp = 0;
            for (int i = pairs.length - 1; i >= 0; i--) {
                temp += pairs[i].value;
                result = Math.max(result, temp - (c - pairs[i].length));
            }
        }

        final List<Long> halfAccumulation = new ArrayList<>();
        halfAccumulation.add(0L);
        final List<Long> halfAccumulationWithReturn = new ArrayList<>();
        halfAccumulationWithReturn.add(0L);
        if (centerIndex != 0) {
            halfAccumulation.add(pairs[0].value - pairs[0].length);
            halfAccumulationWithReturn.add(pairs[0].value - 2 * pairs[0].length);
            for (int i = 1; i < centerIndex; i++) {
                halfAccumulation.add(halfAccumulation.get(i) + pairs[i].value + pairs[i - 1].length - pairs[i].length);
                halfAccumulationWithReturn.add(halfAccumulationWithReturn.get(i) + pairs[i].value + 2 * pairs[i - 1].length - 2 * pairs[i].length);
            }
        }

        final List<Long> otherAccumulation = new ArrayList<>();
        otherAccumulation.add(0L);
        final List<Long> otherAccumulationWithReturn = new ArrayList<>();
        otherAccumulationWithReturn.add(0L);
        if (n - centerIndex != 0) {
            otherAccumulation.add(pairs[n - 1].value - (c - pairs[n - 1].length));
            otherAccumulationWithReturn.add(pairs[n - 1].value - 2 * (c - pairs[n - 1].length));
            for (int i = 1; i < n - centerIndex - 1; i++) {
                otherAccumulation.add(otherAccumulation.get(i) + pairs[n - i - 1].value + (c - pairs[n - i].length) - (c - pairs[n - i - 1].length));
                otherAccumulationWithReturn.add(otherAccumulationWithReturn.get(i) + pairs[n - i - 1].value + 2 * (c - pairs[n - i].length) - 2 * (c - pairs[n - i - 1].length));
            }
        }

        halfAccumulation.sort(Comparator.reverseOrder());
        halfAccumulationWithReturn.sort(Comparator.reverseOrder());
        otherAccumulation.sort(Comparator.reverseOrder());
        otherAccumulationWithReturn.sort(Comparator.reverseOrder());

        final long temp = Math.max(halfAccumulation.get(0) + otherAccumulationWithReturn.get(0), halfAccumulationWithReturn.get(0) + otherAccumulation.get(0));

        System.out.println(Math.max(Math.max(temp, result), 0));
    }

    static class Pair {
        long length;
        long value;

        Pair(final long length, final long value) {
            this.length = length;
            this.value = value;
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
