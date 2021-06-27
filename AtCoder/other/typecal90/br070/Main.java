package AtCoder.other.typecal90.br070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

// 中央値を選ぶのが最適らしい
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair> list = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());

        final List<Integer> xList = list.stream()
            .map(p -> p.x)
            .collect(Collectors.toList());
        final List<Integer> yList = list.stream()
            .map(p -> p.y)
            .collect(Collectors.toList());
        final long answer = ternarySearch(-1_000_000_000, 1_000_000_000, xList) + ternarySearch(-1_000_000_000, 1_000_000_000, yList);
        System.out.println(answer);
    }

    private static long ternarySearch(final long begin, final long end, final List<Integer> list) {
        if (end - begin <= 3) {
            return LongStream.rangeClosed(begin, end)
                .map(l -> list.stream()
                    .mapToLong(v -> Math.abs(v - l))
                    .sum()
                )
                .min()
                .orElseThrow();
        }

        final long smaller = list.stream()
            .mapToLong(v -> Math.abs(v - (begin * 2 + end) / 3))
            .sum();
        final long bigger = list.stream()
            .mapToLong(v -> Math.abs(v - (begin + end * 2) / 3))
            .sum();
        if (smaller < bigger) {
            return ternarySearch(begin, (begin + end * 2) / 3, list);
        } else {
            return ternarySearch((begin * 2 + end) / 3, end, list);
        }
    }

    private static class Pair {
        final int x;
        final int y;

        Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
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
