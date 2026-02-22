package other.typecal90.ay051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 半分全列挙
public class Main {
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Long> upperBoundComparator = (x, y) -> x > y ? 1 : -1;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final long p = scanner.nextLong();
        final List<Long> list = Stream.generate(scanner::nextLong)
            .limit(n)
            .collect(Collectors.toList());
        final Map<Integer, List<Long>> former = calc(list.subList(0, n / 2));
        final Map<Integer, List<Long>> latter = calc(list.subList(n / 2, n));
        long sum = 0;
        for (final Map.Entry<Integer, List<Long>> entry : former.entrySet()) {
            final int key = entry.getKey();
            if (!latter.containsKey(k - key)) {
                continue;
            }

            final List<Long> src = entry.getValue();
            final List<Long> target = latter.get(k - key);
            for (final long value : src) {
                final int index = ~Collections.binarySearch(target, p - value, upperBoundComparator);
                sum += index;
            }
        }

        System.out.println(sum);
    }

    private static Map<Integer, List<Long>> calc(final List<Long> list) {
        final boolean[] used = new boolean[list.size()];
        final Map<Integer, List<Long>> map = new HashMap<>();
        final Consumer<Integer> recursive = new Consumer<>() {
            @Override
            public void accept(final Integer index) {
                if (index == list.size()) {
                    final long sum = IntStream.range(0, list.size())
                        .filter(i -> used[i])
                        .mapToLong(list::get)
                        .sum();
                    final long count = IntStream.range(0, list.size())
                        .filter(i -> used[i])
                        .count();
                    map.computeIfAbsent((int) count, k -> new ArrayList<>()).add(sum);
                    return;
                }

                used[index] = true;
                accept(index + 1);
                used[index] = false;
                accept(index + 1);
            }
        };
        recursive.accept(0);
        for (final List<Long> value : map.values()) {
            Collections.sort(value);
        }
        return map;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
