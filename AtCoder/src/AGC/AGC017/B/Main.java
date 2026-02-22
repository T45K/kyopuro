package AGC.AGC017.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
数学チック 折れ線グラフみたいなイメージ
足す順番と引く順番は入れ替えても構わないので，ある2<=i<=N-1に対してi番目までを全て足し，それ以降を全て引くとする
すると，i番目の時点でaから到達できる範囲R1はa+c*(i-1)<=R1<=a+d*(i-1)となる
また，逆方向から考えると，i時点でbから到達できる範囲R2はb+c*(N-i)<=R2<=b+d*(N-i)
なので，R1とR2の範囲が被っていれば到達可能となる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextInt();
        final long a = scanner.nextInt();
        final long b = scanner.nextInt();
        final long c = scanner.nextInt();
        final long d = scanner.nextInt();

        for (long i = 2; i < n; i++) {
            final long formerLower = c * (i - 1);
            final long latterLower = c * (n - i);
            final long formerUpper = d * (i - 1);
            final long latterUpper = d * (n - i);
            if (!(a + formerLower > b + latterUpper || a + formerUpper < b + latterLower)) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
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
