package other.aising2020.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
普段のMODをpopcount+-1にした感じ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String x = scanner.next();
        final int popCount = popCount(x);
        if (popCount == 0) {
            for (int i = 0; i < n; i++) {
                System.out.println(1);
            }
            return;
        }

        if (popCount == 1) {
            final long[] incPow = new long[n]; // x.charAt(i) == 0
            incPow[0] = 1;
            long incOrig = x.charAt(n - 1) == '1' ? 1 : 0;
            for (int i = 1; i < n; i++) {
                incPow[i] = incPow[i - 1] * 2 % (popCount + 1);
                if (x.charAt(n - i - 1) == '1') {
                    incOrig += incPow[i];
                    incOrig %= (popCount + 1);
                }
            }
            for (int i = 0; i < n; i++) {
                long tmp;
                int tmpPopCount;
                if (x.charAt(i) - '0' == 0) {
                    tmp = (incOrig + incPow[n - i - 1]) % (popCount + 1);
                    tmpPopCount = popCount + 1;
                } else {
                    System.out.println(0);
                    continue;
                }
                if (tmp == 0) {
                    System.out.println(1);
                    continue;
                }
                int count = 0;
                while (tmp > 0) {
                    tmp %= tmpPopCount;
                    tmpPopCount = popCount(tmp);
                    count++;
                }
                System.out.println(count);
            }
            return;
        }

        final long[] incPow = new long[n]; // x.charAt(i) == 0
        final long[] decPow = new long[n]; // x.charAt(i) == 1
        incPow[0] = 1;
        decPow[0] = 1;
        long incOrig = x.charAt(n - 1) == '1' ? 1 : 0;
        long decOrig = x.charAt(n - 1) == '1' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            incPow[i] = incPow[i - 1] * 2 % (popCount + 1);
            decPow[i] = decPow[i - 1] * 2 % (popCount - 1);
            if (x.charAt(n - i - 1) == '1') {
                incOrig += incPow[i];
                incOrig %= (popCount + 1);
                decOrig += decPow[i];
                decOrig %= (popCount - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            long tmp;
            int tmpPopCount;
            if (x.charAt(i) - '0' == 0) {
                tmp = (incOrig + incPow[n - i - 1]) % (popCount + 1);
                tmpPopCount = popCount + 1;
            } else {
                tmp = (decOrig + popCount - 1 - decPow[n - i - 1]) % (popCount - 1);
                tmpPopCount = popCount - 1;
            }
            if (tmp == 0) {
                System.out.println(1);
                continue;
            }
            int count = 0;
            while (tmp > 0) {
                tmp %= tmpPopCount;
                tmpPopCount = popCount(tmp);
                count++;
            }
            System.out.println(count);
        }
    }

    private static int popCount(final long n) {
        return popCount(Long.toBinaryString(n));
    }

    private static int popCount(final String s) {
        return (int) IntStream.range(0, s.length())
            .mapToObj(i -> s.charAt(i) - '0')
            .filter(i -> i == 1)
            .count();
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
    }
}
