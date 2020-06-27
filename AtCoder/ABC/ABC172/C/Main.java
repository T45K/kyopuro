package AtCoder.ABC.ABC172.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();

        final long[] arrayA = new long[n];
        arrayA[0] = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            arrayA[i] = arrayA[i - 1] + scanner.nextInt();
        }

        final long[] arrayB = new long[m];
        arrayB[0] = scanner.nextInt();
        for (int i = 1; i < m; i++) {
            arrayB[i] = arrayB[i - 1] + scanner.nextInt();
        }

        final long result = Arrays.binarySearch(arrayB, k);
        long max = result >= 0 ? result + 1 : ~result;
        for (int i = 0; i < n; i++) {
            if (arrayA[i] > k) {
                break;
            }

            final long tmp = Arrays.binarySearch(arrayB, k - arrayA[i]);
            max = Math.max(max, i + 1 + (tmp >= 0 ? tmp + 1 : ~tmp));
        }

        System.out.println(max);
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
    