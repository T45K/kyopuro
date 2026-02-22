package ABC.ABC301.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final long n = scanner.nextLong();

        long cur = 0;
        long max = -1;
        for (int digit = 0, exponent = s.toCharArray().length - 1; digit < s.toCharArray().length; digit++, exponent--) {
            final char c = s.charAt(digit);
            if (c == '?') {
                if ((cur | (long) Math.pow(2, exponent)) <= n) {
                    max = Math.max(max, cur | calcRest(s.toCharArray(), digit));
                    cur = cur | (long) Math.pow(2, exponent);
                }
            } else if (c == '1') {
                cur = cur | (long) Math.pow(2, exponent);
            }
        }

        if (cur <= n) {
            System.out.println(cur);
        } else if (max <= n) {
            System.out.println(max);
        } else {
            System.out.println(-1);
        }
    }

    private static long calcRest(final char[] chars, final int digitExclusive) {
        return IntStream.range(digitExclusive + 1, chars.length)
            .mapToLong(i -> chars[i] == '1' || chars[i] == '?' ? (long) Math.pow(2, chars.length - i - 1) : 0)
            .reduce(0, (a, b) -> a | b);
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
