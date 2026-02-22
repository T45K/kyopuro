package ABC.ABC191.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long x = parse(scanner.next());
        final long y = parse(scanner.next());
        final long r = parse(scanner.next());
        long sum = 0;
        for (long tmp = (y + r) / 10_000 * 10_000; tmp >= y - r; tmp -= 10_000) {
            final long len = r * r - (tmp - y) * (tmp - y);
            final long rightSide = rightBinarySearch(x, x + (long) Math.sqrt(len) + 10_000, x, len) / 10_000;
            final long tmpLeftSide = leftBinarySearch(x - (long) Math.sqrt(len) - 10_000, x, x, len);
            final long leftSide = tmpLeftSide > 0 ? (tmpLeftSide + 9999) / 10_000 : -tmpLeftSide / 10_000 * (-1);
            sum += rightSide - leftSide + 1;
        }
        System.out.println(sum);
    }

    private static long rightBinarySearch(final long min, final long max, final long x, final long len) {
        if (max - min <= 1) {
            return min;
        }
        final long mid = (min + max) / 2;
        final long tmp = (mid - x) * (mid - x);
        if (tmp == len) {
            return mid;
        } else if (tmp < len) {
            return rightBinarySearch(mid, max, x, len);
        } else {
            return rightBinarySearch(min, mid, x, len);
        }
    }

    private static long leftBinarySearch(final long min, final long max, final long x, final long len) {
        if (max - min <= 1) {
            return max;
        }
        final long mid = (min + max) / 2;
        final long tmp = (mid - x) * (mid - x);
        if (tmp == len) {
            return mid;
        } else if (tmp > len) {
            return leftBinarySearch(mid, max, x, len);
        } else {
            return leftBinarySearch(min, mid, x, len);
        }
    }

    private static long parse(final String str) {
        if (str.contains(".")) {
            final String[] elements = str.split("\\.");
            final long l = 10000L * Math.abs(Integer.parseInt(elements[0]));
            switch (elements[1].length()) {
                case 1:
                    return l + 1_000L * Integer.parseInt(elements[1]);
                case 2:
                    return l + 100L * Integer.parseInt(elements[1]);
                case 3:
                    return l + 10L * Integer.parseInt(elements[1]);
                default:
                    return l + Integer.parseInt(elements[1]);
            }
        } else {
            return 10_000L * Math.abs(Integer.parseInt(str));
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
    }
}
