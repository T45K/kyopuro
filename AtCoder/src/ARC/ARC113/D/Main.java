package ARC.ARC113.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
Aiの最大値がK'の時、全ての値がK'以上である行が少なくとも一つ存在する
この時、各BjにK'以上K以下の任意の値を配置できる。
 */
public class Main {
    private static final long MOD = 998244353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();

        if (n == 1 && m == 1) {
            System.out.println(k);
            return;
        }

        // 列を適当に決めると行は一意に決まる
        if (n == 1) {
            System.out.println(iterativePow(k, m));
            return;
        }

        // 同上
        if (m == 1) {
            System.out.println(iterativePow(k, n));
            return;
        }

        IntStream.rangeClosed(1, k)
            .mapToLong(i -> {
                final long col = modSub(iterativePow(i, n), iterativePow(i - 1, n));
                final long row = iterativePow(k - i + 1, m);
                return modPro(col, row);
            })
            .reduce(Main::modSum)
            .ifPresent(System.out::println);
    }

    private static long iterativePow(long a, long b) {
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
                tmp %= MOD;
            }
            a *= a;
            a %= MOD;
            b >>= 1;
        }

        return tmp;
    }

    private static long modSum(final long a, final long b) {
        return (a + b) % MOD;
    }

    private static long modSub(final long a, final long b) {
        return (a + MOD - b) % MOD;
    }

    private static long modPro(final long a, final long b) {
        return a * b % MOD;
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
    