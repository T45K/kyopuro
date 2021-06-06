package AtCoder.ABC.ABC204.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final int[][] table = new int[n + 1][100_001]; // index: Aの時間 value: Bの時間
        for (final int[] array : table) {
            Arrays.fill(array, Integer.MAX_VALUE / 2);
        }
        table[0][0] = 0;
        for (int i = 0; i < n; i++) {
            final int value = list.get(i);
            for (int j = 0; j <= 100_000 - value; j++) {
                table[i + 1][j] = Math.min(table[i + 1][j], table[i][j] + value);
                table[i + 1][j + value] = Math.min(table[i + 1][j + value], table[i][j]);
            }
        }
        final int answer = IntStream.rangeClosed(1, 100_000)
            .map(i -> Math.max(i, table[n][i]))
            .min()
            .orElseThrow();
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
