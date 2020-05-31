package AtCoder.ABC.ABC169.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列
勘で解いた
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair> list = IntStream.range(0, n)
            .mapToObj(i -> new Pair(scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());

        final List<Integer> aSortedList = list.stream()
            .map(pair -> pair.a)
            .sorted()
            .collect(Collectors.toList());

        final List<Integer> bSortedList = list.stream()
            .map(pair -> pair.b)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        if (n % 2 == 1) {
            System.out.println(bSortedList.get(n / 2) - aSortedList.get(n / 2) + 1);
            return;
        }

        final int a = (n - 1) / 2;
        final int b = n / 2;
        final double formerBegin = aSortedList.get(a);
        final double formerEnd = bSortedList.get(b);
        final double latterBegin = aSortedList.get(b);
        final double latterEnd = bSortedList.get(a);

        final double begin = (formerBegin + latterBegin) / 2;
        final double end = (formerEnd + latterEnd) / 2;
        System.out.println((int) ((end - begin) * 2 + 1));
    }

    private static class Pair {
        final int a;
        final int b;

        Pair(final int a, final int b) {
            this.a = a;
            this.b = b;
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
