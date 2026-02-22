package ABC.ABC192.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
伝家の宝刀
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String x = scanner.next();
        final long m = scanner.nextLong();
        final int l = x.length();
        if (l == 1) {
            final int xInt = Integer.parseInt(x);
            if (xInt <= m) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
            return;
        }
        final long d = IntStream.range(0, l)
            .map(i -> x.charAt(i) - '0')
            .max()
            .orElseThrow();

        final long search = binarySearch(d, (long) 1e18 + 1, x, m);
        System.out.println(search - d);
    }

    private static long binarySearch(final long begin, final long end, final String x, final long m) {
        if (end - begin <= 1) {
            return begin;
        }

        final long mid = (begin + end) / 2;
        final BigInteger bigMid = BigInteger.valueOf(mid);
        final BigInteger bigM = BigInteger.valueOf(m);
        BigInteger sum = BigInteger.ZERO;
        BigInteger digit = BigInteger.ONE;
        for (int i = 0; i < x.length(); i++) {
            if (i > 0) {
                digit = digit.multiply(bigMid);
            }
            sum = sum.add(BigInteger.valueOf(x.charAt(x.length() - i - 1) - '0').multiply(digit));
            if (digit.compareTo(bigM) > 0 || sum.compareTo(bigM) > 0) {
                return binarySearch(begin, mid, x, m);
            }
        }
        if (sum.compareTo(bigM) > 0) {
            return binarySearch(begin, mid, x, m);
        } else {
            return binarySearch(mid, end, x, m);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
