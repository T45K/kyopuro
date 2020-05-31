package AtCoder.ABC.ABC169.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .collect(Collectors.toList());
        final boolean zeroContained = list.stream().anyMatch(i -> i == 0);
        if (zeroContained) {
            System.out.println(0);
            return;
        }

        BigInteger product = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            final long a = list.get(i);
            product = product.multiply(BigInteger.valueOf(a));
            final BigInteger max = BigInteger.valueOf(1_000_000_000_000_000_000L);
            if (product.compareTo(max) > 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(product.longValue());
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
