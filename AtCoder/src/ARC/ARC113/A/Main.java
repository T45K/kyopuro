package ARC.ARC113.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
メモしといた上でナイーブにやる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int k = scanner.nextInt();
        int pre = -1;
        long preCount = -1;
        long count = 0;
        for (int i = 1; i <= k; i++) {
            final int div = k / i;
            if (div == pre) {
                count += preCount;
                continue;
            }
            long count2 = 0;
            for (int j = 1; j <= div; j++) {
                count2 += div / j;
            }
            pre = div;
            preCount = count2;
            count += count2;
        }
        System.out.println(count);
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
