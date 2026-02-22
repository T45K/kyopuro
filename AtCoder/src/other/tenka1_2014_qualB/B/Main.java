package other.tenka1_2014_qualB.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
文字列 文字列の長さが10^3，数が10^2なので全探索できる
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final List<String> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.next())
                .collect(Collectors.toList());

        final long[] array = new long[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (final String t : list) {
                if (t.length() > s.length() - i) {
                    continue;
                }
                if (s.startsWith(t, i)) {
                    final int end = i + t.length() - 1;
                    array[end] += i > 0 ? array[i - 1] : 1;
                    array[end] %= MOD;
                }
            }
        }
        System.out.println(array[s.length() - 1]);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
    