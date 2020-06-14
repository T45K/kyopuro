package AtCoder.ABC.ABC170.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数学 解説AC
エラトステネスの篩の感覚
値を昇順に見ていき，とりあえず倍数は全て消す
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .sorted()
            .collect(Collectors.toList());

        final int max = list.get(list.size() - 1);

        final List<Integer> duplicatedNumbers = list.stream()
            .collect(Collectors.groupingBy(Integer::intValue))
            .entrySet().stream()
            .filter(e -> e.getValue().size() >= 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        final boolean[] array = new boolean[max + 1];
        Arrays.fill(array, true);
        final Set<Integer> candidates = new HashSet<>();
        for (final int value : list) {
            if (!array[value]) {
                continue;
            }

            candidates.add(value);
            for (int i = 2; i * value <= max; i++) {
                array[i * value] = false;
            }
        }

        duplicatedNumbers.forEach(candidates::remove);
        System.out.println(candidates.size());
    }

    private static class FastScanner implements AutoCloseable {
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

        @Override
        public void close() throws Exception {
            reader.close();
        }
    }
}
