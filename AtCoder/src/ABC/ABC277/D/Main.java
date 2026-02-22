package ABC.ABC277.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);

        // given
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        // solve
        final Map<Integer, Long> counts = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final List<Integer> keys = counts.keySet().stream().sorted().collect(Collectors.toList());
        if (getLast(keys) == keys.size() - 1) {
            System.out.println(0);
            return;
        }

        List<Integer> tmp = new ArrayList<>();
        final List<List<Integer>> groups = new ArrayList<>();
        tmp.add(keys.get(0));
        for (int i = 1; i < keys.size(); i++) {
            final int value = keys.get(i);
            if (getLast(tmp) + 1 == value) {
                tmp.add(value);
            } else {
                groups.add(tmp);
                tmp = new ArrayList<>();
                tmp.add(value);
            }
        }

        if (!tmp.isEmpty()) {
            if ((getLast(keys) + 1) % m == keys.get(0)) {
                groups.get(0).addAll(tmp);
            } else {
                groups.add(tmp);
            }
        }

        final long sum = list.stream().mapToLong(Integer::longValue).sum();
        final long min = groups.stream()
            .mapToLong(group -> sum - group.stream().mapToLong(key -> key * counts.get(key)).sum())
            .min()
            .orElseThrow();
        System.out.println(min);
    }

    private static <T> T getLast(final List<T> list) {
        return list.get(list.size() - 1);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
