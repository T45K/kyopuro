package AtCoder.other.practice2.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            final long n = scanner.nextInt();
            final long m = scanner.nextInt();
            final long a = scanner.nextInt();
            final long b = scanner.nextInt();
            System.out.println(floorSum(n, m, a, b));
        }
    }

    private static long floorSum(final long n, final long m, long a, long b) {
        long answer = 0;
        if (a >= m) {
            answer += (n - 1) * n * (a / m) / 2;
            a %= m;
        }
        if (b >= m) {
            answer += n * (b / m);
            b %= m;
        }

        final long yMax = (a * n + b) / m;
        final long xMax = yMax * m - b;
        if (yMax == 0) {
            return answer;
        }
        answer += (n - (xMax + a - 1) / a) * yMax;
        answer += floorSum(yMax, a, m, (a - xMax % a) % a);
        return answer;
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
