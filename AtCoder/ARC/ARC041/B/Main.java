package AtCoder.ARC.ARC041.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[][] table = new int[n][m];
        for (int i = 0; i < n; i++) {
            final String s = scanner.next();
            for (int j = 0; j < m; j++) {
                table[i][j] = s.charAt(j) - '0';
            }
        }

        final int[][] previous = new int[n][m];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                final int min = min(table[i - 1][j], table[i + 1][j], table[i][j - 1], table[i][j + 1]);
                previous[i][j] = min;
                table[i - 1][j] -= min;
                table[i + 1][j] -= min;
                table[i][j - 1] -= min;
                table[i][j + 1] -= min;
            }
        }

        final String answer = Arrays.stream(previous)
            .map(array -> Arrays.stream(array)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining()))
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static int min(final int... values) {
        return Arrays.stream(values).min().orElseThrow();
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
