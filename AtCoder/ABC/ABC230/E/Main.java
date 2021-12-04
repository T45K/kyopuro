package AtCoder.ABC.ABC230.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. ある変数Mを用意し，Nで初期化する
2. Nを割った商がMになる最大の数と最小の数を求める
3. この時，最大の数がNになったら終了
4. Mを，Nを最大の数+1で割った時の商に更新し，1から繰り返す
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        long div = n;
        long sum = 0;
        while (true) {
            final long a = lowerBound(0, n + 1, div + 1, n);
            final long b = lowerBound(0, n + 1, div, n);
            sum += div * (b - a);
            if (b == n) {
                break;
            }
            div = n / (b + 1);
        }
        System.out.println(sum);
    }

    private static long lowerBound(final long begin, final long end, final long div, final long n) {
        if (end - begin <= 1) {
            return begin;
        }

        final long mid = (begin + end) / 2;
        final long tmp = n / mid;
        if (tmp >= div) {
            return lowerBound(mid, end, div, n);
        } else {
            return lowerBound(begin, mid, div, n);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
