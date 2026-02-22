package other.digitalarts.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
文字列 列挙するだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();

        final long count = IntStream.range(0, s.length())
                .mapToObj(s::charAt)
                .distinct()
                .count();

        final char initial = s.charAt(0);
        if (count > 1) {
            System.out.println(s.substring(1) + initial);
            return;
        }

        if (initial == 'z' && s.length() == 20 || initial == 'a' && s.length() == 1) {
            System.out.println("NO");
            return;
        }

        if (initial == 'z') {
            System.out.println(s.substring(0, s.length() - 1) + 'y' + 'a');
        } else if (initial == 'a') {
            System.out.println(s.substring(0, s.length() - 2) + 'b');
        } else if (s.length() == 1) {
            System.out.println("a" + (char) (initial - 1));
        } else {
            System.out.println(s.substring(0, s.length() - 2) + (char) (initial - 1) + (char) (initial + 1));
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
