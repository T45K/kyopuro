package AtCoder.other.typecal90.AB028;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
二次元いもす法
 */
public class Main {
    private static final int MAX = 1_000;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[][] table = new int[MAX + 1][MAX + 1];
        for (int i = 0; i < n; i++) {
            final int lx = scanner.nextInt();
            final int ly = scanner.nextInt();
            final int rx = scanner.nextInt();
            final int ry = scanner.nextInt();
            table[lx][ly]++; // 左下
            table[rx][ly]--; // 右下の右
            table[lx][ry]--; // 左上の上
            table[rx][ry]++; // 右上の右上
        }

        for (int i = 0; i <= MAX; i++) {
            for (int j = 1; j <= MAX; j++) {
                table[i][j] += table[i][j - 1];
            }
        }

        for (int i = 1; i <= MAX; i++) {
            for (int j = 0; j <= MAX; j++) {
                table[i][j] += table[i - 1][j];
            }
        }

        final Map<Integer, Long> count = Arrays.stream(table, 0, 1001)
            .flatMap(array -> Arrays.stream(array, 0, 1001).boxed())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final String answer = IntStream.rangeClosed(1, n)
            .mapToObj(count::get)
            .map(it -> it == null ? 0 : it)
            .map(Objects::toString)
            .collect(Collectors.joining(System.lineSeparator()));
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
