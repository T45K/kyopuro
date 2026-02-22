package ARC.ARC103.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
木 直感で解いた 証明は解説を参照
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final int n = s.length();
        final boolean[] array = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = s.charAt(i - 1) == '1';
        }

        if (array[n] || !array[1] || !array[n - 1]) {
            System.out.println(-1);
            return;
        }

        for (int i = 1; i <= n / 2; i++) {
            if (array[i] != array[n - i]) {
                System.out.println(-1);
                return;
            }
        }

        if (IntStream.range(2, n - 1).mapToObj(i -> array[i]).noneMatch(Boolean::booleanValue)) {
            for (int i = 2; i <= n; i++) {
                System.out.println("1 " + i);
            }
            return;
        }

        int parent = 1;
        for (int i = 2; i <= n; i++) {
            System.out.println(parent + " " + i);
            if (array[i - 1]) {
                parent = i;
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
