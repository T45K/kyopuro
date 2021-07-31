package AtCoder.ABC.ABC212.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final char[] array = scanner.next().toCharArray();
        if (array[0] == array[1] && array[1] == array[2] && array[2] == array[3]) {
            System.out.println("Weak");
            return;
        }
        if ((array[1] - '0') == (array[0] - '0' + 1) % 10
            && (array[2] - '0') == (array[1] - '0' + 1) % 10
            && (array[3] - '0') == (array[2] - '0' + 1) % 10) {
            System.out.println("Weak");
            return;
        }

        System.out.println("Strong");
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
