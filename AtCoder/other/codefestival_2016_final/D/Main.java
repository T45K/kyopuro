package AtCoder.other.codefestival_2016_final.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
和がMの倍数になるペアを優先的に組むのが最適
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final Map<Integer, List<Integer>> map = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.groupingBy(k -> k % m));
        final Function<Integer, List<Integer>> getList = key -> Optional.ofNullable(map.get(key)).orElse(Collections.emptyList());
        int sum = getList.apply(0).size() / 2; // 0は同値ペアしか組めない
        if (m % 2 == 0) { // 折り返し地点は同値ペアしか組めない
            sum += getList.apply(m / 2).size() / 2;
        }
        sum += IntStream.range(1, (m + 1) / 2)
            .map(i -> {
                final List<Integer> former = getList.apply(i);
                final List<Integer> latter = getList.apply(m - i);
                if (former.size() == latter.size()) {
                    return former.size();
                } else {
                    return Math.min(former.size(), latter.size()) + calcSameValuePair(former, latter);
                }
            }).sum();
        System.out.println(sum);
    }

    private static int calcSameValuePair(final List<Integer> list1, final List<Integer> list2) {
        final List<Integer> larger = list1.size() > list2.size() ? list1 : list2;
        final List<Integer> smaller = list1.size() < list2.size() ? list1 : list2;
        final int surplus = larger.size() - smaller.size();
        final int tmp = larger.stream()
            .collect(Collectors.groupingBy(Function.identity()))
            .values().stream()
            .filter(list -> list.size() >= 2)
            .mapToInt(list -> list.size() / 2)
            .sum();
        return Math.min(surplus / 2, tmp);
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
