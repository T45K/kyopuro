package ABC.ABC173.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
いい加減bit全探索を覚える
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int k = scanner.nextInt();

        final boolean[][] table = new boolean[h][w];
        int all = 0;
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                table[i][j] = s.charAt(j) == '#';
                if (s.charAt(j) == '#') {
                    all++;
                }
            }
        }

        if (all < k) {
            System.out.println(0);
            return;
        }

        final int answer = recursiveH(0, h, w, table, k);
        System.out.println(answer);
    }

    private static int recursiveH(final int current, final int h, final int w, final boolean[][] table, final int k) {
        if (current == h) {
            return recursiveW(0, h, w, table, k);
        }

        final boolean[] tmp = new boolean[w];
        int sum = 0;
        for (int i = 0; i < w; i++) {
            tmp[i] = table[current][i];
            table[current][i] = false;
        }
        sum += recursiveH(current + 1, h, w, table, k);
        System.arraycopy(tmp, 0, table[current], 0, w);
        sum += recursiveH(current + 1, h, w, table, k);

        return sum;
    }

    private static int recursiveW(final int current, final int h, final int w, final boolean[][] table, final int k) {
        if (current == w) {
            return count(table) == k ? 1 : 0;
        }

        final boolean[] tmp = new boolean[h];
        int sum = 0;
        for (int i = 0; i < h; i++) {
            tmp[i] = table[i][current];
            table[i][current] = false;
        }
        sum += recursiveW(current + 1, h, w, table, k);
        for (int i = 0; i < h; i++) {
            table[i][current] = tmp[i];
        }
        sum += recursiveW(current + 1, h, w, table, k);

        return sum;
    }

    private static int count(final boolean[][] table) {
        return (int) Arrays.stream(table)
            .mapToLong(array -> IntStream.range(0, array.length)
                .mapToObj(i -> array[i])
                .filter(b -> b)
                .count())
            .sum();
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
    