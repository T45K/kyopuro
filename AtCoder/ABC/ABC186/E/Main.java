package AtCoder.ABC.ABC186.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/*
解説AC
https://atcoder.jp/contests/abc186/editorial/401
数学
Ax ≡ B mod Nのとき、次のようにしてxを求められる。
1. A,B,NをGCDで割る
2. GCD(A,N) != 1 なら解なし
3. GCD(A,N) = 1 なら x = B * modInv(A)
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            final long n = scanner.nextInt();
            final long s = scanner.nextInt();
            final long k = scanner.nextInt();
            final long m = (n - s) % n;
            final long d = euclideanAlgorithm(n, k, m);
            final long n2 = n / d;
            final long k2 = k / d;
            final long m2 = m / d;
            if (euclideanAlgorithm(k2, n2) > 1) {
                System.out.println(-1);
                continue;
            }
            final long answer = BigInteger.valueOf(k2).modInverse(BigInteger.valueOf(n2)).longValue() * m2 % n2;
            System.out.println(answer);
        }
    }

    private static long euclideanAlgorithm(final long a, final long b, final long c) {
        return euclideanAlgorithm(euclideanAlgorithm(a, b), c);
    }

    private static long euclideanAlgorithm(final long a, final long b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
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
