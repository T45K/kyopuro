package other.typecal90.S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
DP
ワーシャルフロイドの感覚
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt() * 2;
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final long[][] table = new long[n][n];
        for (int interval = 1; interval < n; interval += 2) {
            for (int from = 0; from + interval < n; from++) {
                final int to = from + interval;
                if (interval == 1) {
                    table[from][to] = Math.abs(array[to] - array[from]);
                    continue;
                }
                table[from][to] = Math.abs(array[to] - array[from]) + table[from + 1][to - 1];
                for (int relay = 1; relay < interval; relay += 2) {
                    table[from][to] = Math.min(table[from][to], table[from][from + relay] + table[from + relay + 1][to]);
                }
            }
        }
        System.out.println(table[0][n - 1]);
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
