package AtCoder.ABC.ABC037.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
全探索を高速化する系 今回はメモ化 Scannerがメモリを大量に食ってそうなので，MLEが多発するときはFastScannerを使う
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();

        final int[][][] cube = new int[h][w][2];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                final int a = scanner.nextInt();
                cube[i][j][0] = a;
            }
        }

        long sum = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (cube[i][j][1] == 0) {
                    dfs(cube, i, j, h, w);
                }
                sum += cube[i][j][1];
                sum %= MOD;
            }
        }
        System.out.println(sum);
    }

    private static void dfs(final int[][][] cube, final int i, final int j, final int h, final int w) {
        long sum = 1;
        if (i > 0 && cube[i][j][0] < cube[i - 1][j][0]) {
            if (cube[i - 1][j][1] == 0) {
                dfs(cube, i - 1, j, h, w);
            }
            sum += cube[i - 1][j][1];
            sum %= MOD;
        }
        if (i < h - 1 && cube[i][j][0] < cube[i + 1][j][0]) {
            if (cube[i + 1][j][1] == 0) {
                dfs(cube, i + 1, j, h, w);
            }
            sum += cube[i + 1][j][1];
            sum %= MOD;
        }
        if (j > 0 && cube[i][j][0] < cube[i][j - 1][0]) {
            if (cube[i][j - 1][1] == 0) {
                dfs(cube, i, j - 1, h, w);
            }
            sum += cube[i][j - 1][1];
            sum %= MOD;
        }
        if (j < w - 1 && cube[i][j][0] < cube[i][j + 1][0]) {
            if (cube[i][j + 1][1] == 0) {
                dfs(cube, i, j + 1, h, w);
            }
            sum += cube[i][j + 1][1];
            sum %= MOD;
        }
        cube[i][j][1] = (int) sum;
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
