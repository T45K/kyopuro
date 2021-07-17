package AtCoder.other.typecal90.ch086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
解説AC
何だかんだ言って全探索
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Constraint> list = Stream.generate(
            () -> new Constraint(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextLong())
        )
            .limit(q)
            .collect(Collectors.toList());

        final int exponentiation = 1 << n;
        final long answer = IntStream.range(0, 60)
            .mapToLong(digit -> IntStream.range(0, exponentiation)
                .filter(bit -> list.stream()
                    .allMatch(constraint -> {
                        final int x = isIncluded(bit, constraint.x) ? 1 : 0;
                        final int y = isIncluded(bit, constraint.y) ? 1 : 0;
                        final int z = isIncluded(bit, constraint.z) ? 1 : 0;
                        return (x | y | z) == (isIncluded(constraint.w, digit + 1) ? 1 : 0);
                    }))
                .count())
            .reduce(1, (a, b) -> a * b % MOD);
        System.out.println(answer);
    }

    private static boolean isIncluded(final long bit, final int value) {
        return (bit & (1L << (value - 1))) > 0;
    }

    private static class Constraint {
        final int x;
        final int y;
        final int z;
        final long w;

        Constraint(final int x, final int y, final int z, final long w) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
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
