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

/*
解説AC
バラして尺取
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        long sum = 0;
        final List<Integer> tmp = new ArrayList<>();
        for (final int value : list) {
            if (y <= value && value <= x) {
                tmp.add(value);
                continue;
            }

            sum += calc(tmp, x, y);
            tmp.clear();
        }
        sum += calc(tmp, x, y);
        System.out.println(sum);
    }

    private static long calc(final List<Integer> values, final int max, final int min) {
        final int size = values.size();
        int minCount = 0;
        int maxCount = 0;

        long sum = 0;
        int latterIndex = 0;
        for (int formerIndex = 0; formerIndex < size; formerIndex++) {
            if (values.get(formerIndex) == max) {
                maxCount++;
            }
            if (values.get(formerIndex) == min) {
                minCount++;
            }
            for (; latterIndex <= formerIndex; latterIndex++) {
                if (maxCount == 0 || minCount == 0) {
                    break;
                }
                sum += size - formerIndex;
                if (values.get(latterIndex) == max) {
                    maxCount--;
                }
                if (values.get(latterIndex) == min) {
                    minCount--;
                }
            }
        }
        return sum;
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
