package other.typecal90.AJ036;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
解説AC
マンハッタン距離は45°回転させるとうまくいく
x' = x - y
y' = x + y
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Point> list = Stream.generate(() -> {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            return new Point(x - y, x + y);
        })
            .limit(n)
            .collect(Collectors.toList());

        final Point xMax = list.stream()
            .max(Comparator.comparingLong(p -> p.x))
            .orElseThrow();
        final Point xMin = list.stream()
            .min(Comparator.comparingLong(p -> p.x))
            .orElseThrow();
        final Point yMax = list.stream()
            .max(Comparator.comparingLong(p -> p.y))
            .orElseThrow();
        final Point yMin = list.stream()
            .min(Comparator.comparingLong(p -> p.y))
            .orElseThrow();

        final String answer = Stream.generate(() -> {
            final int query = scanner.nextInt();
            final Point point = list.get(query - 1);
            return Collections.max(List.of(Math.abs(point.x - xMax.x), Math.abs(point.x - xMin.x), Math.abs(point.y - yMax.y), Math.abs(point.y - yMin.y)));
        })
            .limit(q)
            .map(Objects::toString)
            .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(answer);
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
