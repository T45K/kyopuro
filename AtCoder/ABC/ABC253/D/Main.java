package AtCoder.ABC.ABC253.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextInt();
        final long a = scanner.nextInt();
        final long b = scanner.nextInt();

        final long allSum = rangeSum(n);
        final long aSum = rangeSum(n / a) * a;
        final long bSum = rangeSum(n / b) * b;
        final long abSum = rangeSum(n / (a * b / euclideanAlgorithm(a, b))) * (a * b / euclideanAlgorithm(a, b));
        System.out.println(allSum - aSum - bSum + abSum);
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

    private static long rangeSum(final long end) {
        return end * (end + 1) / 2;
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
