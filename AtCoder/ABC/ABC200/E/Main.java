package AtCoder.ABC.ABC200.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

        long sum = 0;
        for (long i = 3; i <= 3L * n; i++) { // 和がiの時，i-1個の隙間から二つ選んで壁を入れる．
            // ただし，間隔はN以内
            final long comb = (i - 1) * (i - 2) / 2;
            if (sum + comb < k) {
                sum += comb;
                continue;
            }
            for (long j = 1; ; j++) {
                final long tmp = i - j - 1;
                if (sum + tmp < k) {
                    sum += tmp;
                    continue;
                }
                final long center = k - tmp - j;
                System.out.println(j + " " + (k - tmp + j) + " " + (i - k + tmp - j));
                return;
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
