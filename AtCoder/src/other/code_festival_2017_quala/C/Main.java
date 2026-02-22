package other.code_festival_2017_quala.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int[] counts = new int[26];
        for (int i = 0; i < h; i++) {
            final String a = scanner.next();
            for (int j = 0; j < w; j++) {
                counts[a.charAt(j) - 'a']++;
            }
        }

        if (h % 2 == 0 && w % 2 == 0) {
            if (Arrays.stream(counts).allMatch(i -> i % 4 == 0)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else if (h % 2 == 1 && w % 2 == 1) {
            final boolean res = Arrays.stream(counts).filter(i -> i % 2 == 1).count() == 1 &&
                Arrays.stream(counts).map(i -> i % 2 == 0 ? i : (i - 1)).filter(i -> i % 4 != 0).count() <= (h + w - 2) / 2;
            if (res) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } else {
            final boolean res = Arrays.stream(counts).allMatch(i -> i % 2 == 0) &&
                Arrays.stream(counts).filter(i -> i % 2 == 0 && i % 4 != 0).count() <= (h % 2 == 0 ? h : w) / 2;
            if (res) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
