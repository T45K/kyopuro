package AtCoder.other.m_solutions2020.D;

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
貪欲
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());

        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1000L);
        for (int i = 0; i < n; i++) {
            final Map<Long, Long> tmp = new HashMap<>();
            final int stock = list.get(i);
            for (final Map.Entry<Long, Long> entry : map.entrySet()) {
                tmp.compute(entry.getKey(), (k, v) -> v == null ? entry.getValue() : Math.max(v, entry.getValue()));

                final long sell = entry.getValue() + entry.getKey() * stock;
                tmp.compute(0L, (k, v) -> v == null ? sell : Math.max(v, sell));

                final long buy = entry.getKey() + entry.getValue() / stock;
                final long rest = entry.getValue() % stock;
                tmp.compute(buy, (k, v) -> v == null ? rest : Math.max(v, rest));
            }
            map = tmp;
        }

        map.values().stream()
            .mapToLong(Long::longValue)
            .max()
            .ifPresent(System.out::println);
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
