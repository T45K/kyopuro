package AtCoder.ARC.ARC021.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数学チック a^a=0になる性質を利用する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int l = scanner.nextInt();
        final List<Long> list = IntStream.range(0, l)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        final long allXor = list.stream()
                .reduce((a, b) -> a ^ b)
                .orElseThrow(RuntimeException::new);
        if (allXor != 0) {
            System.out.println(-1);
            return;
        }

        final long[] array = new long[l];
        array[0] = 0;
        for (int i = 1; i < l; i++) {
            array[i] = array[i - 1] ^ list.get(i - 1);
        }

        Arrays.stream(array)
                .forEach(System.out::println);
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
    