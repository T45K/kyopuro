package AtCoder.ABC.ABC234.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long x = scanner.nextLong();
        if (x < 100) {
            System.out.println(x);
            return;
        }

        for (int i = Long.toString(x).length(); i < 19; i++) {
            final int[] array = new int[i];
            for (int j = 1; j < 10; j++) {
                array[0] = j;
                label:
                for (int k = 0; k < 10; k++) {
                    array[1] = k;
                    final int diff = k - j;
                    for (int l = 2; l < i; l++) {
                        final int tmp = array[l - 1] + diff;
                        if (tmp < 0 || tmp > 9) {
                            continue label;
                        }
                        array[l] = tmp;
                    }
                    final long value = Long.parseLong(Arrays.stream(array)
                        .mapToObj(Objects::toString)
                        .collect(Collectors.joining()));
                    if (value >= x) {
                        System.out.println(value);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
