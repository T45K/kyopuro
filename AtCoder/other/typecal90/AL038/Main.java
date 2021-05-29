package AtCoder.other.typecal90.AL038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();
        final long gcd = euclideanAlgorithm(a, b);
        final BigInteger multiply = BigInteger.valueOf(a)
            .multiply(BigInteger.valueOf(b))
            .divide(BigInteger.valueOf(gcd));
        if (multiply.compareTo(BigInteger.valueOf(1_000_000_000_000_000_000L)) > 0) {
            System.out.println("Large");
        } else {
            System.out.println(multiply.longValue());
        }
    }

    private static long euclideanAlgorithm(final long a, final long b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
    