package other.aising2020.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;

/*
最後は解の公式
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        IntStream.rangeClosed(1, n)
            .map(i -> IntStream.rangeClosed(1, (int) sqrt(i))
                .map(x -> (int) IntStream.rangeClosed(1, (int) sqrt(i))
                    .filter(y -> {
                            final int tmp = 4 * i - 2 * x * y - 3 * x * x - 3 * y * y;
                            if (tmp <= 0) {
                                return false;
                            }

                            final int root = (int) sqrt(tmp);
                            final int z = (-(x + y) + root) / 2;
                            return z > 0 && given(x, y, z) == i;
                        }
                    )
                    .count()
                )
                .sum()
            ).forEach(System.out::println);
    }

    private static int given(final int x, final int y, final int z) {
        return x * x + y * y + z * z + x * y + y * z + z * x;
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
    