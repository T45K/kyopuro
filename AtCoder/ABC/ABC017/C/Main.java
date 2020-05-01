package AtCoder.ABC.ABC017.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
数列 "宝石1を含まない遺跡に全て行く","2を~"...と考える
遅延伝播セグ木でも殴れそうだが，素直にimos法
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] array = new int[m + 1];
        for (int i = 0; i < n; i++) {
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            final int s = scanner.nextInt();

            array[0] += s;
            array[l] -= s;

            if (r < m) {
                array[r + 1] += s;
            }
        }

        for (int i = 1; i <= m; i++) {
            array[i] += array[i - 1];
        }

        Arrays.stream(array, 1, m + 1)
                .max()
                .ifPresent(System.out::println);
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
    