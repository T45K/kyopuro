package AtCoder.AGC.AGC048.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char[] AT_CODER = "atcoder".toCharArray();

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            final int answer = solve(scanner);
            if (answer == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }
        }
    }

    private static int solve(final FastScanner scanner) {
        int min = Integer.MAX_VALUE;
        final char[] s = scanner.next().toCharArray();
        try {
            if (new String(s).startsWith(new String(AT_CODER)) && s.length > AT_CODER.length) {
                return 0;
            }
            int cost = 0;
            for (int i = 0; i < AT_CODER.length; i++) {
                if (s[i] > AT_CODER[i]) {
                    min = Math.min(cost, min);
                    return min;
                }
                if (s[i] == AT_CODER[i]) {
                    for (int j = i + 1; j < s.length; j++) {
                        if (s[j] > AT_CODER[i]) {
                            min = Math.min(min, cost + j - i);
                        }
                    }
                    continue;
                }
                for (int j = i + 1; j < s.length; j++) {
                    if (s[j] > AT_CODER[i]) {
                        min = Math.min(min, j - i + cost);
                        return min;
                    }
                    if (s[j] == AT_CODER[i]) {
                        cost += j - i;
                        final char tmp = s[j];
                        System.arraycopy(s, i, s, i + 1, j - i);
                        s[i] = tmp;
                        break;
                    }
                    if (j == s.length - 1) {
                        return min;
                    }
                }
            }
            return min;
        } catch (final ArrayIndexOutOfBoundsException exception) {
            return min;
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
