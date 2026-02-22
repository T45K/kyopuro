package ABC.ABC291.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Pair<Integer, Integer>> list = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .collect(Collectors.toList());

        final MultiValueMap<Integer, Integer> smallerDirectedGraph = new MultiValueMap<>();
        final MultiValueMap<Integer, Integer> biggerDirectedGraph = new MultiValueMap<>();
        for (final Pair<Integer, Integer> pair : list) {
            smallerDirectedGraph.add(pair.second, pair.first);
            biggerDirectedGraph.add(pair.first, pair.second);
        }

        final List<Integer> smallestNumbers = IntStream.rangeClosed(1, n)
            .filter(i -> !smallerDirectedGraph.containsKey(i))
            .boxed()
            .collect(Collectors.toList());
        if (smallestNumbers.size() != 1) {
            System.out.println("No");
            return;
        }

        int counter = 1;
        final int[] answers = new int[n + 1];
        final Deque<Integer> queue = new ArrayDeque<>();
        queue.add(smallestNumbers.get(0));
        while (!queue.isEmpty()) {
            final int small = queue.pollFirst();
            answers[small] = counter;
            counter++;
            for (final int bigger : biggerDirectedGraph.get(small)) {
                final Set<Integer> upstream = smallerDirectedGraph.get(bigger);
                upstream.remove(small);
                if (upstream.size() == 0) {
                    queue.addLast(bigger);
                }
            }
            if (queue.size() > 1) {
                System.out.println("No");
                return;
            }
        }

        final String answer = Arrays.stream(answers, 1, n + 1)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" "));
        System.out.println("Yes");
        System.out.println(answer);
    }

    private static class MultiValueMap<K, V> extends LinkedHashMap<K, Set<V>> {

        public void add(final K key, final V value) {
            super.computeIfAbsent(key, k -> new LinkedHashSet<>()).add(value);
        }

        @Override
        public Set<V> get(final Object key) {
            return super.getOrDefault(key, Collections.emptySet());
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
