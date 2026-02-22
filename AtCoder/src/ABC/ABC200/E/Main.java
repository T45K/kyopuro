package ABC.ABC200.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
きれいさ，おいしさ，人気度をそれぞれa,b,cとする
a+b+cの小さい順に何通りあるかを確認していく(3 <= sum <= 3*N)
b,cが1以上N以下でないといけないことから，aの範囲は max(1,sum-2n) <= a <= min(n,sum-2)
このとき，bの範囲は max(1,sum-a-n) <= b <= min(n,sum-a-1)
a,bが決まればcは一意に定まる
このことから，a+b+cの和がsumの時
Σ(a=max(1,sum-2n)~min(n,sum-2))(min(n,sum-a-1)-max(1,sum-a-n)+1) 通りの選び方がある
これをO(1)で求める
まず，bの値をaで場合分けする（min，maxを固定する）と
a >= sum-n-1 の時，b = sum-a-1
a <= sum-n-1 の時，b = 2n+a-s+1 となる
そこで，aの最小値と最大値から累積和の公式を用いてsumの時の選び方を求められる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

        long accum = 0;
        for (int i = 3; i <= 3 * n; i++) { // 和がiの時，i-1個の隙間から二つ選んで壁を入れる．
            // ただし，間隔はN以内
            final long sum = i;
            final long border = sum - n - 1;
            final long start = Math.max(1, sum - 2L * n);
            final long end = Math.min(n, sum - 2);
            final Function<Long, Long> calcFormerPattern = // 2n + a - sum + 1
                (last) -> (2L * n - sum + 1) * last + last * (last + 1) / 2;
            final Function<Long, Long> calcLatterPattern = // sum - a - 1
                (last) -> (sum - 1) * last - last * (last + 1) / 2;
            final long comb;
            if (border < start) { // sum - a - 1
                comb = calcLatterPattern.apply(end) - calcLatterPattern.apply(start - 1);
            } else if (end < border) { // 2n + a - sum + 1
                comb = calcFormerPattern.apply(end) - calcFormerPattern.apply(start - 1);
            } else {
                comb = calcLatterPattern.apply(end) - calcLatterPattern.apply(border) +
                    calcFormerPattern.apply(border) - calcFormerPattern.apply(start - 1);
            }
            if (accum + comb < k) {
                accum += comb;
                continue;
            }
            for (long a = start; a <= end; a++) {
                final long tmp = Math.min(n, sum - a - 1) - Math.max(1, sum - a - n) + 1;
                if (accum + tmp < k) {
                    accum += tmp;
                    continue;
                }
                for (long b = Math.max(1, sum - a - n); ; b++) {
                    accum++;
                    if (accum == k) {
                        System.out.println(a + " " + b + " " + (sum - a - b));
                        return;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
