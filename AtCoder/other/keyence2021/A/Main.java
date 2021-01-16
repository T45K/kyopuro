package AtCoder.other.keyence2021.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
"今までで一番大きかった値"と"最新のBとAの最大値を掛けた値"を比較する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> listA = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .collect(Collectors.toList());
        final List<Long> listB = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .collect(Collectors.toList());

        long maxA = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            maxA = Math.max(maxA, listA.get(i));
            max = Math.max(max, maxA * listB.get(i));
            System.out.println(max);
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
    }
}
