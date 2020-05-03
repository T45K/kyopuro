package AtCoder.ABC.ABC166.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列 添字i,jに対して i - j = Ai + Aj => i - Ai = j + Aj を探せば良い
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final Map<Integer, Long> sumCount = new HashMap<>();
        final Map<Integer, Long> diffCount = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            final int sum = i + list.get(i);
            final int diff = i - list.get(i);

            sumCount.compute(sum, (k, v) -> v == null ? 1 : v + 1);
            diffCount.compute(diff, (k, v) -> v == null ? 1 : v + 1);
        }

        long sum = 0;
        for (final Map.Entry<Integer, Long> entry : sumCount.entrySet()) {
            final int key = entry.getKey();
            if (diffCount.get(key) != null) {
                final long count = diffCount.get(key);
                sum += entry.getValue() * count;
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
    