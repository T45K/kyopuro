package AtCoder.other.donuts_2015.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int[] idols = new int[n];
        for (int i = 0; i < n; i++) {
            idols[i] = scanner.nextInt();
        }

        final int[] b = new int[m];
        final int[] c = new int[m];
        final Set<Integer>[] sets = new Set[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
            c[i] = scanner.nextInt();
            sets[i] = new HashSet<>();
            for (int j = 0; j < c[i]; j++) {
                sets[i].add(scanner.nextInt() - 1);
            }
        }

        IntStream.range(0, 1 << n)
            .mapToObj(bit -> IntStream.range(0, n)
                .filter(i -> (bit & (1 << i)) != 0)
                .boxed()
                .collect(Collectors.toList()))
            .filter(list -> list.size() == 9)
            .mapToInt(list -> list.stream()
                .mapToInt(i -> idols[i])
                .sum()
                + IntStream.range(0, m)
                .map(i -> list.stream()
                    .filter(sets[i]::contains)
                    .count() >= 3 ? b[i] : 0)
                .sum())
            .max()
            .ifPresent(System.out::println);
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
