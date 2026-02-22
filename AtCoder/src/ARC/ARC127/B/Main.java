package ARC.ARC127.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int l = scanner.nextInt();
        final int[][] table = new int[n][l];
        final String ternaryString = toTernaryString(n - 1);
        for (int i = 0; i < n; i++) {
            table[i][0] = 2;
            for (int j = 1; j < l - ternaryString.length(); j++) {
                table[i][j] = 0;
            }
        }

        for (int i = 0; i < ternaryString.length(); i++) {
            table[n - 1][l - ternaryString.length() + i] = ternaryString.charAt(i) - '0';
        }

        for (int i = n - 2; i >= 0; i--) {
            table[i][l - 1] = (table[i + 1][l - 1] + 2) % 3;
            for (int j = 1; j < ternaryString.length(); j++) {
                final int k = l - j - 1;
                if (table[i + 1][k + 1] == 0 && table[i][k + 1] > 0) {
                    table[i][k] = (table[i + 1][k] + 2) % 3;
                } else {
                    table[i][k] = table[i + 1][k];
                }
            }
        }

        System.out.println(generateAnswer(n, l, 0, table));
        System.out.println(generateAnswer(n, l, 1, table));
        System.out.println(generateAnswer(n, l, 2, table));
    }

    private static String generateAnswer(final int n, final int l, final int offset, final int[][] table) {
        return IntStream.range(0, n)
            .mapToObj(i -> IntStream.range(0, l)
                .mapToObj(j -> Integer.toString((table[i][j] + offset) % 3))
                .collect(Collectors.joining())
            ).collect(Collectors.joining("\n"));
    }

    private static String toTernaryString(int value) {
        final StringBuilder builder = new StringBuilder();
        while (value > 0) {
            builder.append(value % 3);
            value /= 3;
        }
        return builder.reverse().toString();
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
