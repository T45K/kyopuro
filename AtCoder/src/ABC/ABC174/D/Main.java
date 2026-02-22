package ABC.ABC174.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
Rを左側に寄せる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final long replaceCount = IntStream.range(0, n).filter(i -> s.charAt(i) == 'R').count();
        final long minReplace = Math.min(replaceCount, n - replaceCount);
        if (minReplace == 0) {
            System.out.println(0);
            return;
        }

        final int wFirst = s.indexOf("W");
        final int rEnd = s.lastIndexOf("R");
        if (wFirst > rEnd) {
            System.out.println(0);
            return;
        }

        final long rangedCount = IntStream.rangeClosed(wFirst, rEnd)
            .filter(i -> s.charAt(i) == 'R')
            .count();

        final long minSwap = IntStream.range(wFirst, wFirst + (int) rangedCount)
            .filter(i -> s.charAt(i) == 'W')
            .count();

        System.out.println(Math.min(minReplace, minSwap));
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
    