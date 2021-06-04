package AtCoder.other.typecal90.AR090;

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
        final int q = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        int pos = 0;
        for (int i = 0; i < q; i++) {
            final int t = scanner.nextInt();
            switch (t) {
                case 1: {
                    final int x = (scanner.nextInt() - 1 - pos + n) % n;
                    final int y = (scanner.nextInt() - 1 - pos + n) % n;
                    swap(array, x, y);
                    break;
                }
                case 2: {
                    pos = (pos + 1) % n;
                    scanner.nextInt();
                    scanner.nextInt();
                    break;
                }
                case 3: {
                    final int x = (scanner.nextInt() - 1 - pos + n) % n;
                    scanner.nextInt();
                    System.out.println(array[x]);
                }
            }
        }
    }

    private static void swap(final int[] array, final int x, final int y) {
        final int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
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
