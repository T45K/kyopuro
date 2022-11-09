package AtCoder.ABC.ABC274.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final int BASE = 10_000;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        final boolean[][] yAxis = new boolean[n + 1][BASE * 2 + 1];
        final boolean[][] xAxis = new boolean[n + 1][BASE * 2 + 1];
        yAxis[0][BASE] = true;
        xAxis[1][BASE + list.get(0)] = true;
        for (int i = 2; i <= n; i++) {
            final int v = list.get(i - 1);
            if (i % 2 == 0) { // y
                for (int j = 0; j < yAxis[i - 2].length; j++) {
                    if (!yAxis[i - 2][j]) {
                        continue;
                    }
                    if (j - v >= 0) {
                        yAxis[i][j - v] = true;
                    }
                    if (j + v < BASE * 2 + 1) {
                        yAxis[i][j + v] = true;
                    }
                }
            } else { // x
                for (int j = 0; j < xAxis[i - 2].length; j++) {
                    if (!xAxis[i - 2][j]) {
                        continue;
                    }
                    if (j - v >= 0) {
                        xAxis[i][j - v] = true;
                    }
                    if (j + v < BASE * 2 + 1) {
                        xAxis[i][j + v] = true;
                    }
                }
            }
        }
        if (n % 2 == 0) {
            if (yAxis[n][BASE + y] && xAxis[n - 1][BASE + x]) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else {
            if (yAxis[n - 1][BASE + y] && xAxis[n][BASE + x]) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
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
