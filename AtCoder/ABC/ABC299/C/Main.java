package AtCoder.ABC.ABC299.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        final long count = IntStream.range(0, n)
            .map(s::charAt)
            .distinct()
            .count();
        if (count == 1) {
            System.out.println(-1);
            return;
        }

        final int answer = IntStream.range(0, n)
            .mapToObj(s::charAt)
            .reduce(
                new Intermediate(0, 0),
                (Intermediate intermediate, Character c) -> {
                    if (c == '-') {
                        return new Intermediate(0, intermediate.max);
                    } else {
                        return new Intermediate(intermediate.current + 1, Math.max(intermediate.current + 1, intermediate.max));
                    }
                },
                (lhs, rhs) -> rhs).max;
        System.out.println(answer);
    }

    private static class Intermediate {
        final int current;
        final int max;

        Intermediate(final int current, final int max) {
            this.current = current;
            this.max = max;
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
    }
}
