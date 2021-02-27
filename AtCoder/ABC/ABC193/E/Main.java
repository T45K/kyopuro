package AtCoder.ABC.ABC193.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    private static final String NO_ANSWER = "infinity";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            solve(scanner);
        }
    }

    private static void solve(final FastScanner scanner) {
        final long x = scanner.nextInt();
        final long y = scanner.nextInt();
        final long p = scanner.nextInt();
        final long q = scanner.nextInt();
        final long max = 2 * (x + y) * (p + q);
        long train = x;
        long status = p;
        long trainRemain = y;
        long statusRemain = q;
        while (Math.min(train, status) <= max) {
            if (train == status) {
                System.out.println(train);
                return;
            }
            if (train < status) {
                if (trainRemain > 1) {
                    train++;
                    trainRemain--;
                } else {
                    train += 1 + 2 * x + y;
                    trainRemain = y;
                }
            } else {
                if (statusRemain > 1) {
                    status++;
                    statusRemain--;
                } else {
                    status += 1 + p;
                    statusRemain = q;
                }
            }
        }
        System.out.println(NO_ANSWER);
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
