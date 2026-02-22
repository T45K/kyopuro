package other.codefestival_2016_quelA.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final char[] s = scanner.next().toCharArray();
        int k = scanner.nextInt();
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i] == 'a') {
                continue;
            }
            final int index = s[i] - 'a';
            if (k >= 26 - index) {
                k -= 26 - index;
                s[i] = 'a';
            }
        }

        s[s.length - 1] = (char) ((s[s.length - 1] - 'a' + k) % 26 + 'a');
        System.out.println(new String(s));
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
