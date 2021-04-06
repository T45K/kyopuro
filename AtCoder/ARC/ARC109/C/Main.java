package AtCoder.ARC.ARC109.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
常に左側だけを見ていれば良い
問題設定としては与えられた文字列を繰り返しているだけなので
長さが足りなくなったら文字列をくっつければ良い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        StringBuilder s = new StringBuilder(scanner.next());
        for (int i = 0; i < k; i++) {
            if (s.length() % 2 == 1) {
                s.append(s);
            }
            final StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < s.length() / 2; j++) {
                tmp.append(janken(s.charAt(2 * j), s.charAt(2 * j + 1)));
            }
            s = tmp;
        }
        System.out.println(s.charAt(0));
    }

    private static char janken(final char c1, final char c2) {
        if (c1 == c2) {
            return c1;
        }
        if (c1 == 'P' && c2 == 'R'
            || c1 == 'R' && c2 == 'P') {
            return 'P';
        }
        if (c1 == 'S' && c2 == 'P'
            || c1 == 'P' && c2 == 'S') {
            return 'S';
        }
        return 'R';
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
