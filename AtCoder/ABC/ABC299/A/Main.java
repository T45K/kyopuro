package AtCoder.ABC.ABC299.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int ignored = scanner.nextInt();
        final String s = scanner.next();
        final int firstPipeIndex = s.indexOf('|');
        final int secondPipeIndex = s.indexOf('|', firstPipeIndex + 1);
        final int asteriskIndex = s.indexOf('*');
        if (firstPipeIndex < asteriskIndex && asteriskIndex < secondPipeIndex) {
            System.out.println("in");
        } else {
            System.out.println("out");
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
