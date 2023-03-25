package AtCoder.ABC.ABC295.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int r = scanner.nextInt();
        final int c = scanner.nextInt();
        final int[][] table = new int[r][c];
        for (int i = 0; i < r; i++) {
            final String b = scanner.next();
            for (int j = 0; j < c; j++) {
                switch (b.charAt(j)) {
                    case '.':
                        table[i][j] = 0;
                        break;
                    case '#':
                        table[i][j] = -1;
                        break;
                    default:
                        table[i][j] = b.charAt(j) - '0';
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (0 < table[i][j]) {
                    dfs(table, i, j, r, c);
                }
            }
        }
        final String answer = Arrays.stream(table)
            .map(row -> Arrays.stream(row)
                .mapToObj(v -> v == -1 ? "#" : ".")
                .collect(Collectors.joining()))
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static void dfs(final int[][] table, final int x, final int y, final int r, final int c) {
        final int current = table[x][y];
        if (current == 0) {
            return;
        }

        final BiConsumer<Integer, Integer> next = (nextX, nextY) -> {
            if (table[nextX][nextY] < current - 1) {
                table[nextX][nextY] = current - 1;
                dfs(table, nextX, nextY, r, c);
            }
        };

        if (x > 0) next.accept(x - 1, y);
        if (x + 1 < r) next.accept(x + 1, y);
        if (y > 0) next.accept(x, y - 1);
        if (y + 1 < c) next.accept(x, y + 1);
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
