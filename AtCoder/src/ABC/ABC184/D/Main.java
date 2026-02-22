package ABC.ABC184.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final double[][][] cube = new double[101][101][101];
        cube[a][b][c] = 1;
        double sum = 0;
        for (int i = a; i < 100; i++) {
            for (int j = b; j < 100; j++) {
                for (int k = c; k < 100; k++) {
                    if (cube[i][j][k] == 0) {
                        continue;
                    }
                    cube[i + 1][j][k] += cube[i][j][k] * i / (i + j + k);
                    cube[i][j + 1][k] += cube[i][j][k] * j / (i + j + k);
                    cube[i][j][k + 1] += cube[i][j][k] * k / (i + j + k);
                    final int length = i - a + j - b + k - c + 1;
                    if (i + 1 == 100) {
                        sum += length * cube[i + 1][j][k];
                    }
                    if (j + 1 == 100) {
                        sum += length * cube[i][j + 1][k];
                    }
                    if (k + 1 == 100) {
                        sum += length * cube[i][j][k + 1];
                    }
                }
            }
        }
        System.out.println(sum);
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
    