package AtCoder.ABC.ABC198.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String n = scanner.next();
        int end;
        for (end = n.length(); end > 0; end--) {
            if (n.charAt(end - 1) != '0') {
                break;
            }
        }
        for (int i = 0; i < end / 2; i++) {
            if (n.charAt(i) != n.charAt(end - i - 1)) {
                System.out.println("No");
                return;
            }
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
    