package AtCoder.ARC.ARC035.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
変更された点および辺を経由する経路でワーシャルフロイドを更新する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[][] distances = new int[n + 1][n + 1];
        for (final int[] array : distances) {
            Arrays.fill(array, Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();
            final int min = Math.min(distances[a][b], c);
            distances[a][b] = min;
            distances[b][a] = min;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }

        final int k = scanner.nextInt();
        final String answer = IntStream.range(0, k)
            .mapToLong(loop -> {
                final int x = scanner.nextInt();
                final int y = scanner.nextInt();
                final int z = scanner.nextInt();
                final int min = Math.min(distances[x][y], z);
                distances[x][y] = min;
                distances[y][x] = min;
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        distances[i][j] = min(
                            distances[i][j],
                            distances[i][x] + distances[x][j],
                            distances[i][y] + distances[y][j],
                            distances[i][x] + distances[x][y] + distances[y][j],
                            distances[i][y] + distances[y][x] + distances[x][j]);
                    }
                }
                return sum(distances, n);
            }).mapToObj(Long::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static long sum(final int[][] distances, final int n) {
        return IntStream.rangeClosed(1, n)
            .flatMap(i -> IntStream.rangeClosed(i + 1, n)
                .map(j -> distances[i][j]))
            .mapToLong(i -> i)
            .sum();
    }

    private static int min(final int a, final int b, final int c, final int d, final int e) {
        return Math.min(Math.min(a, b), Math.min(c, Math.min(d, e)));
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
