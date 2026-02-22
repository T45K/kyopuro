package ABC.ABC292.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final int[] yellowCardCounts = new int[n + 1];
        final int[] redCardCounts = new int[n + 1];
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int event = scanner.nextInt();
            final int x = scanner.nextInt();
            switch (event) {
                case 1: {
                    yellowCardCounts[x]++;
                    break;
                }
                case 2: {
                    redCardCounts[x]++;
                    break;
                }
                case 3: {
                    if (yellowCardCounts[x] >= 2 || redCardCounts[x] >= 1) {
                        joiner.add("Yes");
                    } else {
                        joiner.add("No");
                    }
                }
            }
        }
        System.out.println(joiner);
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
