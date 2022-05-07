package AtCoder.ABC.ABC242.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        final String answer = Stream.generate(() -> {
                final int n = scanner.nextInt();
                final String s = scanner.next();
                return solve(n, s);
            }).limit(t)
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static long solve(final int n, final String s) {
        final int half = (n + 1) / 2;
        final long[][] dp = new long[half][2]; // 0: upper, 1: others
        dp[0][0] = 1;
        dp[0][1] = s.charAt(0) - 'A';
        for (int i = 1; i < half; i++) {
            dp[i][0] = 1;
            dp[i][1] = ((s.charAt(i) - 'A') + 26 * dp[i - 1][1]) % MOD;
        }

        if (isEqualOrBiggerThanPalindrome(s)) {
            return (1 + dp[half - 1][1]) % MOD;
        } else {
            return dp[half - 1][1];
        }
    }

    private static boolean isEqualOrBiggerThanPalindrome(final String s) {
        final char[] array = new char[s.length()];
        for (int i = 0; i < (s.length() + 1) / 2; i++) {
            array[i] = s.charAt(i);
            array[s.length() - i - 1] = s.charAt(i);
        }
        final String palindrome = new String(array);
        return s.compareTo(palindrome) >= 0;
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
