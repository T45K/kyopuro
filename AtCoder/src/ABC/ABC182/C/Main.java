package ABC.ABC182.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String n = scanner.next();
        final int k = n.length();
        final int[] digits = new int[10];
        int sum = 0;
        for (final char c : n.toCharArray()) {
            digits[c - '0']++;
            sum += c - '0';
        }

        final int sumOneFourSeven = digits[1] + digits[4] + digits[7];
        final int sumTwoFiveEight = digits[2] + digits[5] + digits[8];
        switch (sum % 3) {
            case 0:
                System.out.println(0);
                break;
            case 1:
                if (k > 1 && sumOneFourSeven >= 1) {
                    System.out.println(1);
                } else if (k > 2 && sumTwoFiveEight >= 2) {
                    System.out.println(2);
                } else {
                    System.out.println(-1);
                }
                break;
            case 2:
                if (k > 1 && sumTwoFiveEight > 0) {
                    System.out.println(1);
                } else if (k > 2 && sumOneFourSeven >= 2) {
                    System.out.println(2);
                } else {
                    System.out.println(-1);
                }
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
