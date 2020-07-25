package AtCoder.ARC.ARC018.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Point> list = IntStream.range(0, n)
            .mapToObj(i -> new Point(scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());

        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    final Point a = list.get(i);
                    final Point b = list.get(j);
                    final Point c = list.get(k);
                    final Point vecA = new Point(b.x - a.x, b.y - a.y);
                    final Point vecB = new Point(c.x - a.x, c.y - a.y);
                    final long area = Math.abs(vecA.x * vecB.y - vecA.y * vecB.x);
                    if (area > 0 && area % 2 == 0) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static class Point {
        final long x;
        final long y;

        Point(final long x, final long y) {
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
