package ABC.ABC179.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
MがNに比べて小さいので鳩の巣原理でループになる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final int x = scanner.nextInt();
        final int m = scanner.nextInt();

        if (n < 10_000_000) {
            long tmp = x;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += tmp;
                tmp = tmp * tmp % m;
            }

            System.out.println(sum);
            return;
        }

        final int[] modIndices = new int[m + 1];
        Arrays.fill(modIndices, -1);
        long prev = x;
        int loopStart = -1;
        int loopEnd = -1;
        final List<Long> list = new ArrayList<>();
        for (int i = 0; i <= m; i++) {
            if (modIndices[(int) prev] != -1) {
                loopStart = modIndices[(int) prev];
                loopEnd = i;
                break;
            }

            modIndices[(int) prev] = i;
            list.add(prev);
            prev = prev * prev % m;
        }

        final long startSum = IntStream.range(0, loopStart)
            .mapToLong(list::get)
            .sum();
        final long oneLoopSum = IntStream.range(loopStart, loopEnd)
            .mapToLong(list::get)
            .sum();
        final long loopCount = (n - loopStart) / (loopEnd - loopStart);
        final long endLoop = (n - loopStart) % (loopEnd - loopStart);
        final long endSum = IntStream.range(loopStart, loopStart + (int) endLoop)
            .mapToLong(list::get)
            .sum();
        System.out.println(startSum + oneLoopSum * loopCount + endSum);
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
    