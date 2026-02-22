package ABC.ABC178.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;

/*
解説AC
45度回転
z = x + y, w = x - yとすると
|xi - xj| + |yi - yj| = max(|zi - zj|, |wi - wj|) となる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Point> list = IntStream.range(0, n)
            .mapToObj(i -> new Point(scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());

        final List<Long> zList = list.stream()
            .map(p -> p.x + p.y)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        final List<Long> wList = list.stream()
            .map(p -> p.x - p.y)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        System.out.println(max(zList.get(0) - zList.get(zList.size() - 1), wList.get(0) - wList.get(wList.size() - 1)));
    }

    private static class Point {
        final long x;
        final long y;

        Point(final int x, final int y) {
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
