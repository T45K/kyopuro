package ABC.ABC211.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static final long MOD = 1_000_000_007;
    private static final Map<Character, Integer> CHOKUDAI_INDEX = new HashMap<>();

    static {
        CHOKUDAI_INDEX.put('c', 0);
        CHOKUDAI_INDEX.put('h', 1);
        CHOKUDAI_INDEX.put('o', 2);
        CHOKUDAI_INDEX.put('k', 3);
        CHOKUDAI_INDEX.put('u', 4);
        CHOKUDAI_INDEX.put('d', 5);
        CHOKUDAI_INDEX.put('a', 6);
        CHOKUDAI_INDEX.put('i', 7);
    }

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final long[][] dp = new long[s.length()][CHOKUDAI_INDEX.size()];
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (i > 1) {
                System.arraycopy(dp[i - 1], 0, dp[i], 0, CHOKUDAI_INDEX.size());
            }

            if (c == 'c') {
                dp[i][0]++;
                continue;
            }

            if (i == 0) {
                continue;
            }

            if (CHOKUDAI_INDEX.containsKey(c)) {
                final int index = CHOKUDAI_INDEX.get(c);
                dp[i][index] += dp[i - 1][index - 1];
                dp[i][index] %= MOD;
            }
        }
        System.out.println(dp[s.length() - 1][CHOKUDAI_INDEX.size() - 1]);
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
