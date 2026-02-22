package ARC.ARC119.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
解説AC
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final String t = scanner.next();

        final int[] sZeroIndex = IntStream.range(0, n)
            .filter(i -> s.charAt(i) == '0')
            .toArray();
        final int[] tZeroIndex = IntStream.range(0, n)
            .filter(i -> t.charAt(i) == '0')
            .toArray();

        if (sZeroIndex.length != tZeroIndex.length) {
            System.out.println(-1);
            return;
        }

        final long count = IntStream.range(0, sZeroIndex.length)
            .filter(i -> sZeroIndex[i] != tZeroIndex[i])
            .count();
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
