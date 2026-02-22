package ABC.ABC244.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int[] s = Stream.generate(scanner::next)
            .limit(3)
            .mapToInt(it -> it.charAt(0))
            .toArray();
        final int[] t = Stream.generate(scanner::next)
            .limit(3)
            .mapToInt(it -> it.charAt(0))
            .toArray();
        if (Arrays.equals(s, t)) {
            System.out.println("Yes");
        } else if (s[0] == t[0] || s[1] == t[1] || s[2] == t[2]) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
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
    }
}
