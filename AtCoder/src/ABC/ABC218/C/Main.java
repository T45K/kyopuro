package ABC.ABC218.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
片方を90°ずつ回転させて左上詰めした物同士を比較する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final boolean[][] s = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            final String row = scanner.next();
            for (int j = 0; j < n; j++) {
                s[i][j] = row.charAt(j) == '#';
            }
        }
        final List<Point> sList1 = convert(s, n);
        final boolean[][] s2 = rotate(s, n);
        final List<Point> sList2 = convert(s2, n);
        final boolean[][] s3 = rotate(s2, n);
        final List<Point> sList3 = convert(s3, n);
        final boolean[][] s4 = rotate(s3, n);
        final List<Point> sList4 = convert(s4, n);

        final boolean[][] t = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            final String row = scanner.next();
            for (int j = 0; j < n; j++) {
                t[i][j] = row.charAt(j) == '#';
            }
        }
        final List<Point> tList = convert(t, n);
        if (Set.of(sList1, sList2, sList3, sList4).contains(tList)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // 反時計回りに90°回転させる
    private static boolean[][] rotate(final boolean[][] source, final int n) {
        final boolean[][] dest = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dest[i][j] = source[j][n - i - 1];
            }
        }
        return dest;
    }

    private static List<Point> convert(final boolean[][] table, final int n) {
        final List<Point> list = IntStream.range(0, n)
            .boxed()
            .flatMap(i -> IntStream.range(0, n)
                .filter(j -> table[i][j])
                .mapToObj(j -> new Point(i, j))
            ).collect(Collectors.toList());
        final int minX = list.stream().mapToInt(point -> point.x).min().orElseThrow();
        final int minY = list.stream().mapToInt(point -> point.y).min().orElseThrow();
        return list.stream()
            .map(point -> new Point(point.x - minX, point.y - minY))
            .sorted(Comparator.comparingInt((Point point) -> point.x).thenComparing(point -> point.y))
            .collect(Collectors.toList());
    }

    private static class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
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
