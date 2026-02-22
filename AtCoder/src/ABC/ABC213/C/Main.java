package ABC.ABC213.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();
        final List<Point> list = Stream.generate(() -> new Point(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> xOrder = list.stream()
            .map(point -> point.x)
            .sorted()
            .distinct()
            .collect(Collectors.toList());
        final List<Integer> yOrder = list.stream()
            .map(point -> point.y)
            .sorted()
            .distinct()
            .collect(Collectors.toList());

        final String answer = list.stream()
            .map(point -> {
                final int orderedX = Collections.binarySearch(xOrder, point.x) + 1;
                final int orderedY = Collections.binarySearch(yOrder, point.y) + 1;
                return orderedX + " " + orderedY;
            })
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Point {
        final int x;
        final int y;

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
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    