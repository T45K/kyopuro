package AtCoder.ABC.ABC221.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
座圧していもす法
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair<Integer, Integer>> list = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());

        final List<Integer> compressed = list.stream()
            .flatMap(pair -> Stream.of(pair.first, pair.first + pair.second))
            .sorted()
            .distinct()
            .collect(Collectors.toList());
        final Map<Integer, Integer> invertedIndex = IntStream.range(0, compressed.size())
            .boxed()
            .collect(Collectors.toMap(compressed::get, Function.identity()));

        final int[] array = new int[invertedIndex.size()];
        for (final Pair<Integer, Integer> pair : list) {
            array[invertedIndex.get(pair.first)]++;
            array[invertedIndex.get(pair.first + pair.second)]--;
        }
        for (int i = 1; i < array.length; i++) {
            array[i] += array[i - 1];
        }
        final int[] answers = new int[n + 1];
        for (int i = 0; i < array.length - 1; i++) {
            answers[array[i]] += compressed.get(i + 1) - compressed.get(i);
        }

        final String answer = Arrays.stream(answers, 1, n + 1)
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
