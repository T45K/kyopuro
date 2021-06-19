package AtCoder.other.typecal90.bc055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int p = scanner.nextInt();
        final int q = scanner.nextInt();
        final long[] array = Stream.generate(scanner::nextLong)
            .limit(n)
            .mapToLong(Long::longValue)
            .toArray();
        int counter = 0;
        for (int a = 0; a < n - 4; a++) {
            final long tmpA = array[a] % p;
            for (int b = a + 1; b < n - 3; b++) {
                final long tmpB = array[b] * tmpA % p;
                for (int c = b + 1; c < n - 2; c++) {
                    final long tmpC = array[c] * tmpB % p;
                    for (int d = c + 1; d < n - 1; d++) {
                        final long tmpD = array[d] * tmpC % p;
                        for (int e = d + 1; e < n; e++) {
                            if (array[e] * tmpD % p == q) {
                                counter++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(counter);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
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
    }
}
    