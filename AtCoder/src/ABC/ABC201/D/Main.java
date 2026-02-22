package ABC.ABC201.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
T-Aを全体のスコアとして，高橋君はスコアを最大に，青木君は最小にするように動く
 */
public class Main {
    private static final String TAKAHASHI = "Takahashi";
    private static final String AOKI = "Aoki";
    private static final String DRAW = "Draw";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int[][] table = new int[h][w];
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = ((i + j) % 2 == 1 ? 1 : -1) * (s.charAt(j) == '+' ? 1 : -1);
            }
        }
        final int[][] score = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                score[i][j] = (i + j) % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        score[h - 1][w - 1] = 0;
        dfs(table, score, h - 1, w - 1, h, w);
        if (score[0][0] == 0) {
            System.out.println(DRAW);
        } else if (score[0][0] > 0) {
            System.out.println(TAKAHASHI);
        } else {
            System.out.println(AOKI);
        }
    }

    // (i+j)%2 == 0のとき，Tに選択権がある．
    private static void dfs(final int[][] table, final int[][] score, final int i, final int j, final int h, final int w) {
        if ((i + j) % 2 == 0) { // turn T
            if (j < w - 1) {
                score[i][j] = Math.max(score[i][j], score[i][j + 1] + table[i][j + 1]);
            }
            if (i < h - 1) {
                score[i][j] = Math.max(score[i][j], score[i + 1][j] + table[i + 1][j]);
            }
        } else { // turn A
            if (j < w - 1) {
                score[i][j] = Math.min(score[i][j], score[i][j + 1] + table[i][j + 1]);
            }
            if (i < h - 1) {
                score[i][j] = Math.min(score[i][j], score[i + 1][j] + table[i + 1][j]);
            }
        }
        if (i == 0 && j == 0) {
            return;
        }
        if (j > 0) {
            dfs(table, score, i, j - 1, h, w);
        } else {
            dfs(table, score, i - 1, w - 1, h, w);
        }
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
