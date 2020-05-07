package AtCoder.other.cf_2015_morning_hard.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列 左右から累積和して全探索
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        final long[] lToR = new long[n];
        lToR[0] = list.get(0);
        for (int i = 1; i < n; i++) {
            lToR[i] = list.get(i) + lToR[i - 1] + 1;
        }
        for (int i = 1; i < n; i++) {
            lToR[i] += lToR[i - 1];
        }

        final long[] rToL = new long[n];
        rToL[n - 1] = list.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            rToL[i] = list.get(i) + rToL[i + 1] + 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            rToL[i] += rToL[i + 1];
        }

        final long answer = IntStream.range(0, n)
                .filter(i -> i % 2 == 0)
                .mapToLong(l -> (l > 1 ? lToR[l - 1] : 0) + (l < n - 1 ? rToL[l + 1] : 0))
                .min()
                .orElse(0);

        System.out.println(answer);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
