package AtCoder.ABC.ABC303.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long aPressed = scanner.nextLong();
        final long shiftAPressed = scanner.nextLong();
        final long capsPressed = scanner.nextLong();
        final String s = scanner.next();
        final long[][] dp = new long[s.length()][2]; // 0: caps off, 1: caps on
        if (s.charAt(0) == 'a') {
            dp[0][0] = aPressed;
            dp[0][1] = capsPressed + shiftAPressed;
        } else {
            dp[0][0] = shiftAPressed;
            dp[0][1] = capsPressed + aPressed;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + capsPressed) + aPressed;
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + capsPressed) + shiftAPressed;
            } else {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + capsPressed) + shiftAPressed;
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + capsPressed) + aPressed;
            }
        }
        System.out.println(Math.min(dp[s.length() - 1][0], dp[s.length() - 1][1]));
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
