package AtCoder.ABC.ABC282.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        @SuppressWarnings("unused") final int n = scanner.nextInt();
        final String s = scanner.next();

        final StringBuilder builder = new StringBuilder();
        boolean isEnclosed = false;
        for (final char c : s.toCharArray()) {
            if (c == '"') {
                builder.append(c);
                isEnclosed = !isEnclosed;
                continue;
            }

            if (c == ',') {
                if (isEnclosed) {
                    builder.append(',');
                } else {
                    builder.append('.');
                }
                continue;
            }

            builder.append(c);
        }

        System.out.println(builder);
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
