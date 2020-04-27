package AtCoder.ABC.ABC164.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
数学
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        if (s.length() < 4) {
            System.out.println(0);
            return;
        }

        final int[] array = new int[s.length()];
        array[s.length() - 1] = s.charAt(s.length() - 1) - '0';
        int tmp = 10;
        for (int i = s.length() - 2; i >= 0; i--) {
            array[i] = ((s.charAt(i) - '0') * tmp + array[i + 1]) % 2019;
            tmp *= 10;
            tmp %= 2019;
        }

        final int[] counts = new int[2020];
        counts[0]++;
        long answer = 0;
        for (int i = s.length() - 1; i - 4 >= 0; i--) {
            answer += counts[array[i - 4]];
            counts[array[i]]++;
        }

        System.out.println(answer);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
    }
}
    