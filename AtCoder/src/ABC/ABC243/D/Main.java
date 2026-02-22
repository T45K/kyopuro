package ABC.ABC243.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
答えの上限が10^18以下であることが保証されてるので、
計算途中でその値を越える計算は無視して良い
 */
public class Main {
    private static final long MAX = 1_000_000_000_000_000_000L;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        @SuppressWarnings("unused") final int n = scanner.nextInt();
        final long x = scanner.nextLong();
        final String s = scanner.next();

        int count = 0;
        long tmp = x;
        for (final char c : s.toCharArray()) {
            switch (c) {
                case 'U': {
                    if (count > 0) {
                        count--;
                    } else {
                        tmp /= 2;
                    }
                    break;
                }
                case 'L': {
                    if (tmp * 2 > MAX) {
                        count++;
                    } else {
                        tmp *= 2;
                    }
                    break;
                }
                case 'R': {
                    if (tmp * 2 + 1 > MAX) {
                        count++;
                    } else {
                        tmp = tmp * 2 + 1;
                    }
                }
            }
        }
        System.out.println(tmp);
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
    }
}
