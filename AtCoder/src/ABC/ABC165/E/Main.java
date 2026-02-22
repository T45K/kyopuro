package ABC.ABC165.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
数列 ある2つを結んだとき，右に何個，左に何個進むパターンは以降使えなくなるので被らないように選ぶ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        if (n % 2 == 1) {
            for (int i = 1; i <= m; i++) {
                System.out.println(i + " " + (n - i + 1));
            }
            return;
        }

        boolean flag = false;
        for (int i = 1; i <= m; i++) {
            if (n - 2 * i + 1 <= n / 2) {
                flag = true;
            }
            if (flag) {
                System.out.println(i + " " + (n - i));
            } else {
                System.out.println(i + " " + (n - i + 1));
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
