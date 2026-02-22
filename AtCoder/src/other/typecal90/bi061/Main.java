package other.typecal90.bi061;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int q = scanner.nextInt();
        final List<Integer> top = new ArrayList<>();
        final List<Integer> bottom = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            final int t = scanner.nextInt();
            final int x = scanner.nextInt();
            switch (t) {
                case 1: {
                    top.add(x);
                    break;
                }
                case 2: {
                    bottom.add(x);
                    break;
                }
                case 3: {
                    if (x <= top.size()) {
                        System.out.println(top.get(top.size() - x));
                    } else {
                        System.out.println(bottom.get(x - top.size() - 1));
                    }
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
