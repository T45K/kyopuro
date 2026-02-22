package other.typecal90.AT046;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> a = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> b = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> c = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final long[][] array = new long[3][46];
        for (final int value : a) {
            array[0][value % 46]++;
        }
        for (final int value : b) {
            for (int i = 0; i < 46; i++) {
                array[1][(i + value) % 46] += array[0][i];
            }
        }
        for (final int value : c) {
            for (int i = 0; i < 46; i++) {
                array[2][(i + value) % 46] += array[1][i];
            }
        }
        System.out.println(array[2][0]);
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
