package AtCoder.ABC.ABC247.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        final List<List<Integer>> lists = cutByOutOfRangeValues(list, x, y);

        long sum = 0;
        for (final List<Integer> values : lists) {
            final int size = values.size();
            int minCount = 0;
            int maxCount = 0;

            int rightIndex = 0;
            for (int leftIndex = 0; leftIndex < size; leftIndex++) {
                if (values.get(leftIndex) == x) {
                    maxCount++;
                }
                if (values.get(leftIndex) == y) {
                    minCount++;
                }
                for (; rightIndex <= leftIndex; rightIndex++) {
                    if (maxCount == 0 || minCount == 0) {
                        break;
                    }
                    sum += size - leftIndex;
                    if (values.get(rightIndex) == x) {
                        maxCount--;
                    }
                    if (values.get(rightIndex) == y) {
                        minCount--;
                    }
                }
            }
        }

        System.out.println(sum);
    }

    private static List<List<Integer>> cutByOutOfRangeValues(final List<Integer> list, final int x, final int y) {
        final List<List<Integer>> lists = new ArrayList<>();
        final List<Integer> tmp = new ArrayList<>();

        for (final int value : list) {
            if (y <= value && value <= x) {
                tmp.add(value);
                continue;
            }
            if (!tmp.isEmpty()) {
                lists.add(tmp);
            }
            tmp.clear();
        }
        if (!tmp.isEmpty()) {
            lists.add(tmp);
        }
        return lists;
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
