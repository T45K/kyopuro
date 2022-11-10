package AtCoder.ABC.ABC273.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int r = scanner.nextInt();
        final int c = scanner.nextInt();
        final int n = scanner.nextInt();
        final List<Point> givenWalls = Stream.generate(() -> new Point(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());
        final int q = scanner.nextInt();
        final List<Question> questions = Stream.generate(() -> new Question(scanner.next().charAt(0), scanner.nextInt()))
            .limit(q)
            .collect(Collectors.toList());

        final List<Point> allWalls = Stream.of(
                givenWalls.stream(),
                IntStream.rangeClosed(0, w).mapToObj(i -> new Point(0, i)),
                IntStream.rangeClosed(0, w).mapToObj(i -> new Point(h + 1, i)),
                IntStream.rangeClosed(1, h - 1).mapToObj(i -> new Point(i, 0)),
                IntStream.rangeClosed(1, h - 1).mapToObj(i -> new Point(i, w + 1))
            )
            .flatMap(Function.identity())
            .collect(Collectors.toList());

        final Map<Integer, List<Integer>> horizontal = allWalls.stream()
            .collect(Collectors.groupingBy(
                point -> point.x,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .map(point -> point.y)
                        .sorted()
                        .collect(Collectors.toList()))
            ));

        final Map<Integer, List<Integer>> vertical = allWalls.stream()
            .collect(Collectors.groupingBy(
                point -> point.y,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .map(point -> point.x)
                        .sorted()
                        .collect(Collectors.toList())
                )
            ));

        final StringJoiner joiner = new StringJoiner("\n");
        int currentR = r;
        int currentC = c;
        for (final Question question : questions) {
            switch (question.d) {
                case 'L': {
                    final int index = ~Collections.binarySearch(horizontal.get(currentR), currentC);
                    final int wallPosition = horizontal.get(currentR).get(index - 1);
                    final int space = currentC - wallPosition - 1;
                    currentC -= Math.min(question.l, space);
                    break;
                }
                case 'R': {
                    final int index = ~Collections.binarySearch(horizontal.get(currentR), currentC);
                    final int wallPosition = horizontal.get(currentR).get(index);
                    final int space = wallPosition - currentC - 1;
                    currentC += Math.min(question.l, space);
                    break;
                }
                case 'U': {
                    final int index = ~Collections.binarySearch(vertical.get(currentC), currentR);
                    final int wallPosition = vertical.get(currentC).get(index - 1);
                    final int space = currentR - wallPosition - 1;
                    currentR -= Math.min(question.l, space);
                    break;
                }
                case 'D': {
                    final int index = ~Collections.binarySearch(vertical.get(currentC), currentR);
                    final int wallPosition = vertical.get(currentC).get(index);
                    final int space = wallPosition - currentR - 1;
                    currentR += Math.min(question.l, space);
                    break;
                }
                default:
                    throw new RuntimeException();
            }
            joiner.add(currentR + " " + currentC);
        }
        System.out.println(joiner);
    }

    private static class Question {
        final char d;
        final int l;

        Question(final char d, final int l) {
            this.d = d;
            this.l = l;
        }
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
