package AtCoder.other.code_festival_2016_quelC.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int k = scanner.nextInt();
        final int t = scanner.nextInt();
        final int max = IntStream.range(0, t)
            .map(i -> scanner.nextInt())
            .max()
            .orElseThrow();
        if (max <= (k + 1) / 2) {
            System.out.println(0);
            return;
        }

        final boolean[] placed = new boolean[k];
        for (int i = 0; i < max; i++) {
            if (i * 2 < k) {
                placed[i * 2] = true;
                continue;
            }
            if (k % 2 == 1) {
                placed[2 * i % k] = true;
            } else {
                placed[k - 2 * i % k - 1] = true;
            }
        }
        final long answer = IntStream.range(1, k)
            .filter(i -> placed[i - 1] && placed[i])
            .count();
        System.out.println(answer);
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
