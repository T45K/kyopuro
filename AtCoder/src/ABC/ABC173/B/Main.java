package ABC.ABC173.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final String AC = "AC";
    private static final String WA = "WA";
    private static final String TLE = "TLE";
    private static final String RE = "RE";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        int ac = 0;
        int wa = 0;
        int tle = 0;
        int re = 0;
        for (int i = 0; i < n; i++) {
            final String s = scanner.next();
            switch (s) {
                case AC:
                    ac++;
                    break;
                case WA:
                    wa++;
                    break;
                case TLE:
                    tle++;
                    break;
                case RE:
                    re++;
                    break;
            }
        }

        System.out.println(AC + " x " + ac);
        System.out.println(WA + " x " + wa);
        System.out.println(TLE + " x " + tle);
        System.out.println(RE + " x " + re);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
    