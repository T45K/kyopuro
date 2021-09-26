package AtCoder.ABC.ABC222.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
DP
 */
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();

        final long[][] table = new long[n][10];
        table[1][f(array[0], array[1])]++;
        table[1][g(array[0], array[1])]++;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                final int f = f(j, array[i]);
                table[i][f] += table[i - 1][j];
                table[i][f] %= MOD;
                final int g = g(j, array[i]);
                table[i][g] += table[i - 1][j];
                table[i][g] %= MOD;
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(table[n - 1][i]);
        }
    }

    private static int f(final int a, final int b) {
        return (a + b) % 10;
    }

    private static int g(final int a, final int b) {
        return a * b % 10;
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
