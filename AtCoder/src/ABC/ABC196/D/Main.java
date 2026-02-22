package ABC.ABC196.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
全探索
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        if (a == 0) {
            System.out.println(1);
            return;
        }
        final boolean[][] isPlacable = new boolean[h][w];
        for (final boolean[] array : isPlacable) {
            Arrays.fill(array, true);
        }
        final long answer = recursive(0, 0, h, w, a, isPlacable);
        System.out.println(answer);
    }

    private static long recursive(final int i, final int j, final int h, final int w, final int rest, final boolean[][] isPlacable) {
        if (i == h - 1 && j == w - 1) {
            return rest == 0 ? 1 : 0;
        }

        final int nextI = (i < h - 1) ? i + 1 : 0;
        final int nextJ = (i < h - 1) ? j : j + 1;
        // 置けない
        if (!isPlacable[i][j]) {
            return recursive(nextI, nextJ, h, w, rest, isPlacable);
        }

        //置ける
        long sum = 0;
        // 置かない
        sum += recursive(nextI, nextJ, h, w, rest, isPlacable);
        isPlacable[i][j] = false;
        // 縦に置く
        if (i < h - 1 && isPlacable[i + 1][j]) {
            isPlacable[i + 1][j] = false;
            sum += recursive(nextI, nextJ, h, w, rest - 1, isPlacable);
            isPlacable[i + 1][j] = true;
        }
        // 横に置く
        if (j < w - 1 && isPlacable[i][j + 1]) {
            isPlacable[i][j + 1] = false;
            sum += recursive(nextI, nextJ, h, w, rest - 1, isPlacable);
            isPlacable[i][j + 1] = true;
        }
        isPlacable[i][j] = true;
        return sum;
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
