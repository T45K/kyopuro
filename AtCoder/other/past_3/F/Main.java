package AtCoder.other.past_3.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[][] table = new int[n][26];
        for (int i = 0; i < n; i++) {
            final String s = scanner.next();
            for (int j = 0; j < n; j++) {
                table[i][s.charAt(j) - 'a']++;
            }
        }

        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (n + 1) / 2; i++) {
            final int[] array1 = table[i];
            final int[] array2 = table[n - i - 1];
            final int index = IntStream.range(0, 26)
                .filter(j -> array1[j] > 0 && array2[j] > 0)
                .findFirst()
                .orElse(-1);

            if (index == -1) {
                System.out.println(-1);
                return;
            }

            builder.append((char) ('a' + index));
        }

        if (n % 2 == 0) {
            System.out.println(builder.toString() + builder.reverse().toString());
        } else {
            System.out.println(builder.toString() + builder.reverse().toString().substring(1));
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
