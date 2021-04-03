package AtCoder.ABC.ARC004.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n];
        array[0] = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            array[i] = array[i - 1] + scanner.nextInt();
        }
        final int all = array[n - 1];
        System.out.println(all);
        final int answer;
        switch (n) {
            case 1: {
                answer = all;
                break;
            }
            case 2: {
                answer = Math.abs(array[1] - 2 * array[0]);
                break;
            }
            default: {
                answer = IntStream.range(0, n - 2)
                    .flatMap(i -> IntStream.range(i + 1, n - 1)
                        .map(j -> {
                            final int first = array[i];
                            final int second = array[j] - array[i];
                            final int third = array[n - 1] - array[j];
                            final int max = max(first, second, third);
                            return Math.max(max - (first + second + third - max), 0);
                        }))
                    .min()
                    .orElseThrow();
            }
        }
        System.out.println(answer);
    }

    private static int max(final int... values) {
        return Arrays.stream(values).max().orElseThrow();
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
    