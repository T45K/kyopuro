package AtCoder.ABC.ABC181.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final int[] digits = new int[10];
        for (final char c : s.toCharArray()) {
            digits[c - '0']++;
        }

        for (int i = 8; i < 1000; i += 8) {
            final int[] targetDigits = new int[10];
            final char[] target;
            if (s.length() == 1) {
                if (i >= 10) {
                    break;
                }
                target = Integer.toString(i).toCharArray();
            } else if (s.length() == 2) {
                if (i >= 100) {
                    break;
                }
                target = String.format("%02d", i).toCharArray();
            } else {

                target = String.format("%03d", i).toCharArray();
            }
            for (final char c : target) {
                targetDigits[c - '0']++;
            }
            boolean flag = true;
            for (int j = 0; j < targetDigits.length; j++) {
                if (digits[j] < targetDigits[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
