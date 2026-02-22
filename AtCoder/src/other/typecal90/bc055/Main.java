package other.typecal90.bc055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int p = scanner.nextInt();
        final int q = scanner.nextInt();
        final long[] array = Stream.generate(scanner::nextLong)
            .limit(n)
            .mapToLong(Long::longValue)
            .toArray();
        final int answer = recursive(0, 0, array, n, p, q, 1);
        System.out.println(answer);
    }

    private static int recursive(final int current, final int index, final long[] array, final int n, final int p, final int q, final long tmp) {
        if (current == 5) {
            return tmp == q ? 1 : 0;
        }

        return IntStream.range(index, n - (4 - current))
            .map(i -> recursive(current + 1, i + 1, array, n, p, q, tmp * array[i] % p))
            .sum();
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
    