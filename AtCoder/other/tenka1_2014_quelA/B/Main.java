package AtCoder.other.tenka1_2014_quelA.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final long[] num = new long[s.length() + 1];
        num[0] = 5;
        final long[] combo = new long[s.length() + 1];
        long damage = 0;
        int charge = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                combo[i] += combo[i - 1];
                num[i] += num[i - 1];
            }

            if (s.charAt(i) == '-' || num[i] == 0 || i < charge) {
                continue;
            }

            if (s.charAt(i) == 'N') {
                num[i]--;
                if (i + 7 < s.length()) num[i + 7]++;
                if (i + 2 < s.length()) combo[i + 2]++;
                damage += 10 + combo[i] / 10;
            } else if (num[i] >= 3) {
                num[i] -= 3;
                if (i + 9 < s.length()) num[i + 9] += 3;
                if (i + 4 < s.length()) combo[i + 4]++;
                damage += 50 + combo[i] / 10 * 5;
                charge = i + 3;
            }
        }
        System.out.println(damage);
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
