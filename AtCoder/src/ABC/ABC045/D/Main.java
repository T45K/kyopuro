package ABC.ABC045.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
実装が重い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();
        final Map<Integer, List<Point>> grouping = Stream.generate(() -> new Point(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.groupingBy(p -> p.x));
        final List<Integer> xValues = grouping.keySet().stream()
            .sorted()
            .collect(Collectors.toList());
        final List<Integer>[] yValues = new List[xValues.size()];
        for (int i = 0; i < xValues.size(); i++) {
            yValues[i] = grouping.get(xValues.get(i)).stream()
                .map(p -> p.y)
                .sorted()
                .collect(Collectors.toList());
        }

        final long[] answers = search(xValues, yValues, h, w);
        final long sum = Arrays.stream(answers).sum();
        answers[0] = (long) (h - 2) * (w - 2) - sum;
        final String answer = Arrays.stream(answers)
            .mapToObj(Long::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static long[] search(final List<Integer> xValues, final List<Integer>[] yValues, final int h, final int w) {
        final long[] counts = new long[10];
        final Set<Long> exists = new HashSet<>();
        IntStream.range(0, xValues.size())
            .forEach(i -> {
                final int xValue = xValues.get(i);
                IntStream.rangeClosed(-2, 0)
                    .filter(xSlide -> xValue + xSlide >= 1 && xValue + xSlide + 2 <= h)
                    .forEach(xSlide -> {
                        for (final int yValue : yValues[i]) {
                            IntStream.rangeClosed(-2, 0)
                                .filter(ySlide -> yValue + ySlide >= 1 && yValue + ySlide + 2 <= w)
                                .filter(ySlide -> {
                                    final long point = (long) (xValue + xSlide) * (w + 1) + yValue + ySlide;
                                    if (exists.contains(point)) {
                                        return false;
                                    }
                                    exists.add(point);
                                    return true;
                                })
                                .forEach(ySlide -> {
                                    final int xStart = binarySearchAfter(xValues, xValue + xSlide);
                                    final int xEnd = binarySearchBefore(xValues, xValue + xSlide + 2);
                                    final int sum = IntStream.rangeClosed(xStart, xEnd)
                                        .map(j -> {
                                            final List<Integer> list = yValues[j];
                                            final int yStart = binarySearchAfter(list, yValue + ySlide);
                                            final int yEnd = binarySearchBefore(list, yValue + ySlide + 2);
                                            return yEnd - yStart + 1;
                                        }).sum();
                                    counts[sum]++;
                                });
                        }
                    });
            });
        return counts;
    }

    private static int binarySearchAfter(final List<Integer> list, final int value) {
        final int index = Collections.binarySearch(list, value);
        return index >= 0 ? index : ~index;
    }

    private static int binarySearchBefore(final List<Integer> list, final int value) {
        final int index = Collections.binarySearch(list, value);
        return index >= 0 ? index : ~index - 1;
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
