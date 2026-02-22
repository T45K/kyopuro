package ABC.ABC208.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[11];
        for (int i = 1; i <= 10; i++) {
            array[i] = factorial(i);
        }

        System.out.println(calc(10, array, n));
    }

    private static long calc(final int index, final int[] coins, final int rest) {
        if (index == 0) {
            return 0;
        }

        return rest / coins[index] + calc(index - 1, coins, rest % coins[index]);
    }

    private static int factorial(final int a) {
        if (a == 1) {
            return 1;
        } else {
            return a * factorial(a - 1);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
