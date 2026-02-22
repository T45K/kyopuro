package ABC.ABC218.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Xでグルーピングして，X,Yが同じ点から答えを求める
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Integer>> grouped = Stream.generate(() -> new Point(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.groupingBy(
                point -> point.x,
                Collectors.mapping(point -> point.y, Collectors.toList())));
        for (final List<Integer> list : grouped.values()) {
            Collections.sort(list);
        }
        final int[] keys = grouped.keySet().stream()
            .mapToInt(Integer::intValue)
            .toArray();

        final long sum = IntStream.range(0, keys.length - 1)
            .filter(i -> grouped.get(keys[i]).size() >= 2)
            .boxed()
            .flatMapToLong(i -> IntStream.range(i + 1, keys.length)
                .filter(j -> grouped.get(keys[j]).size() >= 2)
                .mapToLong(j -> count(grouped.get(keys[i]), grouped.get(keys[j])))
                .map(count -> count * (count - 1) / 2)
            ).sum();
        System.out.println(sum);
    }

    private static long count(final List<Integer> a, final List<Integer> b) {
        long count = 0;
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex < a.size() && bIndex < b.size()) {
            if (a.get(aIndex).equals(b.get(bIndex))) {
                count++;
                aIndex++;
                bIndex++;
            } else if (a.get(aIndex) < b.get(bIndex)) {
                aIndex++;
            } else {
                bIndex++;
            }
        }
        return count;
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
