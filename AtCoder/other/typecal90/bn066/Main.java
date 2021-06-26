package AtCoder.other.typecal90.bn066;

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
期待値は線形
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Range> list = Stream.generate(() -> new Range(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());
        final Double answer = IntStream.range(1, n)
            .mapToDouble(i -> {
                final Range range = list.get(i);
                return IntStream.range(0, i)
                    .mapToObj(list::get)
                    .mapToDouble(tmp -> (double) calculate(range, tmp) / (range.length() * tmp.length()))
                    .sum();
            })
            .sum();
        System.out.println(answer);
    }

    private static int calculate(final Range target, final Range base) {
        return IntStream.rangeClosed(target.l, target.r)
            .map(i -> {
                if (i < base.l) {
                    return base.length();
                } else if (base.r <= i) {
                    return 0;
                } else {
                    return base.r - i;
                }
            })
            .sum();
    }

    private static class Range {
        final int l;
        final int r;

        Range(final int l, final int r) {
            this.l = l;
            this.r = r;
        }

        int length() {
            return this.r - this.l + 1;
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
