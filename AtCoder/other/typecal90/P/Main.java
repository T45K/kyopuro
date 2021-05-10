package AtCoder.other.typecal90.P;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(3)
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
        final int a = array[0];
        final int b = array[1];
        final int c = array[2];
        final long answer = LongStream.range(0, 10_000)
            .takeWhile(i -> a * i <= n)
            .map(i -> {
                final long rest = n - a * i;
                final long min = LongStream.range(0, 10_000)
                    .takeWhile(j -> b * j <= rest)
                    .filter(j -> (rest - b * j) % c == 0)
                    .map(j -> j + (rest - b * j) / c)
                    .min()
                    .orElse(10_000);
                return i + min;
            }).min()
            .orElse(10_000);
        System.out.println(answer);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    