package AtCoder.ABC.ABC260.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
min(a)以下の任意のiについては、[i, max(a)]を含む区間が良い数列になる
max(a)超過min(b)以下の任意のiについては、i以下のAを持つ組のBに対して、[i, max(max(a), max(b))]を含む区間が良い数列になる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Pair<Integer, Integer>> aSortedList = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .sorted(Comparator.comparingInt(p -> p.first))
            .collect(Collectors.toList());
        final int[] acc = new int[m + 2];
        final int aMin = aSortedList.get(0).first;
        final int aLast = aSortedList.get(n - 1).first;
        final int bMin = aSortedList.stream().mapToInt(p -> p.second).min().orElseThrow();
        for (int i = 1; i <= aMin; i++) {
            final int length = aLast - i + 1;
            acc[length]++;
            acc[m - i + 2]--;
        }

        int max = aLast;
        int listIndex = 0;
        for (int i = aMin + 1; i <= bMin; i++) {
            for (; listIndex < aSortedList.size() && aSortedList.get(listIndex).first < i; listIndex++) {
                max = Math.max(max, aSortedList.get(listIndex).second);
            }
            final int length = max - i + 1;
            acc[length]++;
            acc[m - i + 2]--;
        }
        for (int i = 1; i < m + 1; i++) {
            acc[i] += acc[i - 1];
        }

        final String answer = Arrays.stream(acc, 1, m + 1)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" "));
        System.out.println(answer);
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
}
