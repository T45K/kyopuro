package AtCoder.ARC.ARC121.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<List<Long>> lists = Stream.generate(() -> new Pair<>(scanner.nextLong(), scanner.next()))
            .limit(2L * n)
            .sorted(Comparator.comparingLong(pair -> pair.first))
            .collect(Collectors.groupingBy(pair -> pair.second))
            .values().stream()
            .map(list -> list.stream()
                .map(pair -> pair.first)
                .collect(Collectors.toList()))
            .sorted(Comparator.comparingInt(set -> set.size() % 2))
            .collect(Collectors.toList());

        if (lists.stream().allMatch(set -> set.size() % 2 == 0)) {
            System.out.println(0);
            return;
        }

        if (lists.size() == 2) {
            final long min = getMinDiff(lists.get(0), lists.get(1));
            System.out.println(min);
            return;
        }

        final long minA = getMinDiff(lists.get(1), lists.get(2));
        final long minB = getMinDiff(lists.get(0), lists.get(1)) + getMinDiff(lists.get(0), lists.get(2));
        System.out.println(Math.min(minA, minB));
    }

    private static long getMinDiff(final List<Long> a, final List<Long> b) {
        return a.stream()
            .mapToLong(v -> {
                final int index = Collections.binarySearch(b, v);
                if (index >= 0) {
                    return 0;
                }

                final int inverted = ~index;
                if (inverted == 0) {
                    return b.get(0) - v;
                } else if (inverted == b.size()) {
                    return v - b.get(b.size() - 1);
                } else {
                    return Math.min(v - b.get(inverted - 1), b.get(inverted) - v);
                }
            })
            .min()
            .orElseThrow();
    }

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
    