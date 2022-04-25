package AtCoder.ABC.ABC249.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final LinkedHashMap<Integer, Long> map = Stream.generate(scanner::nextInt)
            .limit(n)
            .sorted()
            .collect(Collectors.groupingBy(
                Function.identity(),
                LinkedHashMap::new,
                Collectors.counting()
            ));

        long sum = 0;
        for (final Map.Entry<Integer, Long> entry1 : map.entrySet()) {
            final long key1 = entry1.getKey();
            final long value1 = entry1.getValue();
            if (key1 == 1) {
                sum += value1 * value1 * value1;
                continue;
            }

            for (final Map.Entry<Integer, Long> entry2 : map.entrySet()) {
                final long key2 = entry2.getKey();
                if (key2 * key2 > key1) {
                    break;
                }
                final long value2 = entry2.getValue();
                if (key2 == 1) {
                    sum += value1 * value1 * value2 * 2;
                } else if (key2 * key2 == key1) {
                    sum += value1 * value2 * value2;
                } else if (key1 % key2 == 0 && map.containsKey((int) (key1 / key2))) {
                    sum += value1 * value2 * (map.get((int) (key1 / key2))) * 2;
                }
            }
        }
        System.out.println(sum);
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
