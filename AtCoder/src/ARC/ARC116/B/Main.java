package ARC.ARC116.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Bをソートする
a_iが最大となるとき，計算する部分列は
a_i * (a_i-1 + 2*a_i-2 + 4*a_i-3 + ...)
になる
ので，小さい方から計算していく
 */
public class Main {
    private static final int MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .sorted()
            .collect(Collectors.toList());
        long tmp = 0;
        long sum = 0;
        long prev = 0;
        for (final long value : list) {
            sum += value * value;
            sum %= MOD;
            tmp *= 2;
            tmp %= MOD;
            tmp += prev;
            tmp %= MOD;
            prev = value;
            sum += tmp * value % MOD;
            sum %= MOD;
        }
        System.out.println(sum);
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
