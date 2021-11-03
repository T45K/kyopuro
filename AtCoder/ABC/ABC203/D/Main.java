package AtCoder.ABC.ABC203.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
決め打ちにぶたん
いもす法で対象が中央値になれるかを検証する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final int max = Arrays.stream(table)
            .flatMapToInt(Arrays::stream)
            .max()
            .orElseThrow();

        final int answer = binarySearch(0, max + 1, table, n, k);
        System.out.println(answer);
    }

    private static int binarySearch(final int begin, final int end, final int[][] table, final int n, final int k) {
        if (end - begin <= 1) {
            return begin;
        }

        final int mid = (begin + end) / 2;
        final int[][] counts = creteCountTable(table, n, k, mid);
        final int min = Arrays.stream(counts, k - 1, n)
            .flatMapToInt(array -> Arrays.stream(array, k - 1, n))
            .min()
            .orElseThrow();
        if (min < k * k / 2 + 1) {
            return binarySearch(begin, mid, table, n, k);
        } else {
            return binarySearch(mid, end, table, n, k);
        }
    }

    private static int[][] creteCountTable(final int[][] table, final int n, final int k, final int border) {
        final int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] >= border) {
                    tmp[i][j]++;
                    final int a = i + k;
                    if (a < n) {
                        tmp[a][j]--;
                    }
                    final int b = j + k;
                    if (b < n) {
                        tmp[i][b]--;
                    }
                    if (a < n && b < n) {
                        tmp[a][b]++;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                tmp[i][j] += tmp[i][j - 1];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] += tmp[i - 1][j];
            }
        }
        return tmp;
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    