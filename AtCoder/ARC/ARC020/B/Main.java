package AtCoder.ARC.ARC020.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int c = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Pair> former = IntStream.range(0, n)
            .filter(i -> i % 2 == 0)
            .mapToObj(list::get)
            .collect(Collectors.groupingBy(Function.identity()))
            .entrySet()
            .stream()
            .sorted(Comparator.comparingInt(entry -> -entry.getValue().size()))
            .limit(2)
            .map(entry -> new Pair(entry.getKey(), entry.getValue().size()))
            .collect(Collectors.toList());
        if (former.size() == 1) {
            former.add(new Pair(-1, 0));
        }
        final List<Pair> latter = IntStream.range(0, n)
            .filter(i -> i % 2 == 1)
            .mapToObj(list::get)
            .collect(Collectors.groupingBy(Function.identity()))
            .entrySet()
            .stream()
            .sorted(Comparator.comparingInt(entry -> -entry.getValue().size()))
            .limit(2)
            .map(entry -> new Pair(entry.getKey(), entry.getValue().size()))
            .collect(Collectors.toList());
        if (latter.size() == 1) {
            latter.add(new Pair(-1, 0));
        }
        final Pair formerFirst = former.get(0);
        final Pair latterFirst = latter.get(0);
        if (formerFirst.color != latterFirst.color) {
            System.out.println(c * (n - formerFirst.count - latterFirst.count));
            return;
        }
        final Pair formerSecond = former.get(1);
        final Pair latterSecond = latter.get(1);
        System.out.println(Math.min(c * (n - formerSecond.count - latterFirst.count), c * (n - formerFirst.count - latterSecond.count)));
    }

    private static class Pair {
        final int color;
        final int count;

        Pair(final int color, final int count) {
            this.color = color;
            this.count = count;
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
