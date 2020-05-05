package AtCoder.other.past_1.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();

        final List<String> list = new ArrayList<>();
        int start = 0;
        int end = 1;
        while (start < s.length()) {
            if (s.charAt(end) < 'A' || s.charAt(end) > 'Z') {
                end++;
                continue;
            }
            list.add(s.substring(start, end + 1));
            start = end + 1;
            end = start + 1;
        }

        list.sort(String::compareToIgnoreCase);
        final String answer = String.join("", list);
        System.out.println(answer);
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
