package AtCoder.ABC.ABC297.C;

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
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final char[][] table = new char[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            table[i] = s.toCharArray();
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w - 1; j++) {
                if (table[i][j] == 'T' && table[i][j + 1] == 'T') {
                    table[i][j] = 'P';
                    table[i][j + 1] = 'C';
                }
            }
        }

        final String answer = Arrays.stream(table)
            .map(String::new)
            .collect(Collectors.joining("\n"));
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
