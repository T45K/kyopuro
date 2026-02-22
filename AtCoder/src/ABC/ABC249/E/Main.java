package ABC.ABC249.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
解説AC
dp[i][j]でiが元の文字列の長さ、jが圧縮した文字列の長さでDPする
1~9文字追加したとき、圧縮した文字列の長さは2増え(x1, ... ,x9)、10~99文字の時は3増える(x10, ..., x99)
各桁数ごとに累積和で計算できる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int p = scanner.nextInt();
        final long[][] table = new long[n + 1][n * 2 + 1];
        table[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n * 2; j++) {
                final long current = table[i][j] * (i == 0 ? 26 : 25) % p;
                for (int k = 1, digit = 1; i + k <= n && j + 1 + digit <= n * 2; k *= 10, digit++) {
                    table[i + k][j + 1 + digit] += current;
                    table[i + k][j + 1 + digit] %= p;
                    if (i + k * 10 <= n) {
                        table[i + k * 10][j + 1 + digit] = (table[i + k * 10][j + 1 + digit] + p - current) % p;
                    }
                }

                if (j == 0) {
                    continue;
                }
                table[i + 1][j] += table[i][j];
                table[i + 1][j] %= p;
            }
        }

        final long answer = Arrays.stream(table[n], 1, n)
            .reduce(0, (lhs, rhs) -> (lhs + rhs) % p);
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
