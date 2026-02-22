package ABC.ABC182.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
i回目のロボットの移動は (i-1までの移動) + (数列のiまでの累積和) になる
なので，累積和と，移動中に座標が一番右となる値を記録する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextLong())
            .collect(Collectors.toList());
        final long[] accumArray = new long[n];
        accumArray[0] = list.get(0);
        for (int i = 1; i < n; i++) {
            accumArray[i] = accumArray[i - 1] + list.get(i);
        }
        final long[] rightMax = new long[n];
        rightMax[0] = Math.max(list.get(0), 0);
        for (int i = 1; i < n; i++) {
            rightMax[i] = Math.max(rightMax[i - 1], accumArray[i]);
        }

        long max = Math.max(0, list.get(0));
        long tmp = list.get(0);
        for (int i = 1; i < n; i++) {
            max = Math.max(max, tmp + rightMax[i]);
            tmp += accumArray[i];
        }
        System.out.println(max);
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
