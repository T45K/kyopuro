package ABC.ABC193.B;

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
        final int answer = IntStream.range(0, n)
            .mapToObj(i -> new Triple(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .filter(triple -> triple.x - triple.a > 0)
            .mapToInt(triple -> triple.p)
            .min()
            .orElse(-1);
        System.out.println(answer);
    }

    private static class Triple {
        final int a;
        final int p;
        final int x;

        Triple(final int a, final int p, final int x) {
            this.a = a;
            this.p = p;
            this.x = x;
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
