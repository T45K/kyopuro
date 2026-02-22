package ABC.ABC224.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
値が大きい順に見ていく
同じ行にいる，自分より大きい値を持つマスの最大の移動回数+1回移動できる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        scanner.nextInt();
        scanner.nextInt();
        final int n = scanner.nextInt();
        final List<Point> list = IntStream.range(0, n)
            .mapToObj(index -> new Point(index, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .sorted(Comparator.comparingInt(Point::getA).reversed())
            .collect(Collectors.toList());
        final Map<Integer, Values> rMap = new HashMap<>();
        final Map<Integer, Values> cMap = new HashMap<>();
        final int[] answers = new int[n];
        for (final Point point : list) {
            final Values rValue = rMap.get(point.r);
            final Values cValue = cMap.get(point.c);
            if (rValue == null && cValue == null) {
                answers[point.index] = 0;
                rMap.put(point.r, new Values(point.a, 0, -1));
                cMap.put(point.c, new Values(point.a, 0, -1));
            } else if (cValue == null) {
                final int value;
                if (rValue.a == point.a) {
                    value = rValue.prev + 1;
                } else {
                    value = rValue.cur + 1;
                    rMap.put(point.r, new Values(point.a, value, rValue.cur));
                }
                answers[point.index] = value;
                cMap.put(point.c, new Values(point.a, value, -1));
            } else if (rValue == null) {
                final int value;
                if (cValue.a == point.a) {
                    value = cValue.prev + 1;
                } else {
                    value = cValue.cur + 1;
                    cMap.put(point.c, new Values(point.a, value, cValue.cur));
                }
                answers[point.index] = value;
                rMap.put(point.r, new Values(point.a, value, -1));
            } else {
                if (rValue.a == point.a && cValue.a == point.a) {
                    final int max = Math.max(rValue.prev, cValue.prev) + 1;
                    if (max > rValue.cur) {
                        rMap.put(point.r, new Values(point.a, max, rValue.prev));
                    }
                    if (max > cValue.cur) {
                        cMap.put(point.r, new Values(point.a, max, cValue.prev));
                    }
                    answers[point.index] = max;
                } else if (cValue.a == point.a) {
                    final int max = Math.max(rValue.cur, cValue.prev) + 1;
                    rMap.put(point.r, new Values(point.a, max, rValue.cur));
                    if (max > cValue.cur) {
                        cMap.put(point.c, new Values(point.a, max, cValue.prev));
                    }
                    answers[point.index] = max;
                } else if (rValue.a == point.a) {
                    final int max = Math.max(rValue.prev, cValue.cur) + 1;
                    if (max > rValue.cur) {
                        rMap.put(point.r, new Values(point.a, max, rValue.prev));
                    }
                    cMap.put(point.c, new Values(point.a, max, cValue.cur));
                    answers[point.index] = max;
                } else {
                    final int max = Math.max(rValue.cur, cValue.cur) + 1;
                    rMap.put(point.r, new Values(point.a, max, rValue.cur));
                    cMap.put(point.c, new Values(point.a, max, cValue.cur));
                    answers[point.index] = max;
                }
            }
        }
        final String answer = Arrays.stream(answers)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Values {
        final int a;
        final int cur;
        final int prev;

        public Values(final int a, final int cur, final int prev) {
            this.a = a;
            this.cur = cur;
            this.prev = prev;
        }
    }

    private static class Point {
        final int index;
        final int r;
        final int c;
        final int a;

        Point(final int index, final int r, final int c, final int n) {
            this.index = index;
            this.r = r;
            this.c = c;
            this.a = n;
        }

        public int getA() {
            return a;
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
