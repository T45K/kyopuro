package AtCoder.ABC.ABC217.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
実はTreeSet(Map)にはlower_boundとupper_boundがある
 */
public class MainAlt {

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int l = scanner.nextInt();
        final int q = scanner.nextInt();
        final TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(l);
        final StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < q; i++) {
            final int c = scanner.nextInt();
            final int x = scanner.nextInt();
            if (c == 1) {
                set.add(x);
            } else {
                final int begin = Optional.ofNullable(set.lower(x)).orElseThrow();
                final int end = Optional.ofNullable(set.ceiling(x)).orElseThrow();
                joiner.add(Integer.toString(end - begin));
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
