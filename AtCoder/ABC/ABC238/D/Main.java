package AtCoder.ABC.ABC238.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        final String answer = Stream.generate(() -> {
                final String a = reverse(Long.toBinaryString(scanner.nextLong())); // and
                final String s = reverse(Long.toBinaryString(scanner.nextLong())); // sum
                return solver(a, s);
            }).limit(t)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static String solver(final String a, final String s) {
        int sum = 0;
        for (int i = 0; i < Math.max(a.length(), s.length()); i++) {
            final int ai = charAt(a, i);
            final int si = charAt(s, i);
            if (ai == 0 && si == 0 && sum == 0) { // 0, 0
                sum = 0;
            } else if (ai == 0 && si == 0 && sum == 1) { // 1, 0
                sum = 1;
            } else if (ai == 0 && si == 1 && sum == 0) { // 1, 0
                sum = 0;
            } else if (ai == 0 && si == 1 && sum == 1) { // 0, 0
                sum = 0;
            } else if (ai == 1 && si == 0 && sum == 0) { // 1, 1
                sum = 1;
            } else if (ai == 1 && si == 0 && sum == 1) {
                return "No";
            } else if (ai == 1 && si == 1 && sum == 0) {
                return "No";
            } else { // 1, 1
                sum = 1;
            }
        }
        if (sum == 0) {
            return "Yes";
        } else {
            return "No";
        }
    }

    private static String reverse(final String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static int charAt(final String str, final int i) {
        if (i < str.length()) {
            return str.charAt(i) - '0';
        } else {
            return 0;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
