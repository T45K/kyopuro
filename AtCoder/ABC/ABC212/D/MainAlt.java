package AtCoder.ABC.ABC212.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
解説AC
優先度付きキュー
 */
public class MainAlt {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int q = scanner.nextInt();
        final PriorityQueue<Long> queue = new PriorityQueue<>();
        long accum = 0;
        for (int i = 0; i < q; i++) {
            switch (scanner.nextInt()) {
                case 1: {
                    final int x = scanner.nextInt();
                    queue.add(x - accum);
                    break;
                }
                case 2: {
                    final int x = scanner.nextInt();
                    accum += x;
                    break;
                }
                case 3: {
                    final long poll = Optional.ofNullable(queue.poll()).orElseThrow();
                    System.out.println(poll + accum);
                }
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
