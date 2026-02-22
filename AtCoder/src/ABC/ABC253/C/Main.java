package ABC.ABC253.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int q = scanner.nextInt();
        final TreeMap<Integer, Integer> s = new TreeMap<>();
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int query = scanner.nextInt();
            switch (query) {
                case 1: {
                    final int x = scanner.nextInt();
                    s.compute(x, (k, v) -> v == null ? 1 : v + 1);
                    break;
                }
                case 2: {
                    final int x = scanner.nextInt();
                    final int c = scanner.nextInt();
                    s.compute(x, (k, v) -> v == null ? 0 : v - c);
                    if (s.get(x) <= 0) {
                        s.remove(x);
                    }
                    break;
                }
                case 3: {
                    final int max = s.lastKey();
                    final int min = s.firstKey();
                    joiner.add(Integer.toString(max - min));
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
