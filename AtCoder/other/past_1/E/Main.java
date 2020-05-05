package AtCoder.other.past_1.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();

        final boolean[][] relations = new boolean[n + 1][n + 1];
        for (int i = 0; i < q; i++) {
            final int operation = scanner.nextInt();
            switch (operation) {
                case 1: {
                    final int a = scanner.nextInt();
                    final int b = scanner.nextInt();
                    relations[a][b] = true;
                    break;
                }
                case 2: {
                    final int a = scanner.nextInt();
                    IntStream.rangeClosed(1, n)
                            .filter(j -> relations[j][a])
                            .forEach(follow(relations, a));
                    break;
                }
                case 3: {
                    final int a = scanner.nextInt();
                    IntStream.rangeClosed(1, n)
                            .filter(j -> relations[a][j])
                            .flatMap(j -> IntStream.rangeClosed(1, n)
                                    .filter(k -> relations[j][k]))
                            .distinct()
                            .collect(
                                    IntStream::builder,
                                    IntStream.Builder::accept,
                                    (builder, builder2) -> builder2.build().forEach(builder)
                            )
                            .build()
                            .forEach(follow(relations, a));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    System.out.print("N");
                } else {
                    System.out.print(relations[i][j] ? "Y" : "N");
                }
            }
            System.out.println();
        }
    }

    private static IntConsumer follow(final boolean[][] relations, final int me) {
        return target -> relations[me][target] = true;
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
