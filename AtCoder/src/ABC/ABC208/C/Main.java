package ABC.ABC208.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

        final List<Pair> list = IntStream.rangeClosed(1, n)
            .mapToObj(i -> new Pair(i, scanner.nextInt()))
            .collect(Collectors.toList());

        final long div = k / n;
        final long mod = k % n;
        final Pair boundary = list.stream()
            .sorted(Comparator.comparingInt(pair -> pair.a))
            .limit(mod + 1)
            .reduce((first, second) -> second)
            .orElseThrow();
        final String answer = list.stream()
            .map(pair -> {
                if (pair.a < boundary.a) {
                    return div + 1;
                } else {
                    return div;
                }
            })
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Pair {
        final int i;
        final int a;

        Pair(final int i, final int a) {
            this.i = i;
            this.a = a;
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

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
