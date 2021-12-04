package AtCoder.ABC.ABC230.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        if (s.length() == 1) {
            System.out.println("Yes");
            return;
        }
        if (s.length() == 2) {
            if (s.equals("oo")) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
            return;
        }

        for (int i = 0; i < s.length() - 2; i++) {
            if (s.startsWith("oxx", i)
                || s.startsWith("xxo", i)
                || s.startsWith("xox", i)) {
                continue;
            }
            System.out.println("No");
            return;
        }
        System.out.println("Yes");
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
    }
}
