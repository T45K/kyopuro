package AtCoder.ABC.ABC172.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
エラトステネスの篩の要領
素数で割れるだけ割っていく
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        final long[] array = new long[n + 1];
        Arrays.fill(array, 1);

        final int[] values = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (values[i] == 1) {
                continue;
            }

            for (int j = 1; j * i <= n; j++) {
                int count = 1;
                while (values[i * j] % i == 0) {
                    values[i * j] /= i;
                    count++;
                }
                array[i * j] *= count;
            }
        }

        final long answer = IntStream.rangeClosed(1, n)
            .mapToLong(i -> i * (array[i] * (values[i] > 1 ? 2 : 1)))
            .sum();
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    