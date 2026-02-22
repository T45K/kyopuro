package ABC.ABC223.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
トポロジカルソートでは，入次数が0の時のみキューに追加できる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final ListMap<Integer, Integer> tree = new ListMap<>();
        final int[] in = new int[n + 1];
        Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .distinct()
            .forEach(pair -> {
                tree.putSingle(pair.first, pair.second);
                in[pair.second]++;
            });

        final PriorityQueue<Integer> queue = IntStream.rangeClosed(1, n)
            .filter(i -> in[i] == 0)
            .boxed()
            .collect(Collectors.toCollection(PriorityQueue::new));
        final LinkedHashSet<Integer> answers = new LinkedHashSet<>();
        while (!queue.isEmpty()) {
            final int poll = queue.poll();
            if (answers.contains(poll)) {
                continue;
            }
            answers.add(poll);
            for (final int next : tree.getList(poll)) {
                if (answers.contains(next)) {
                    System.out.println(-1);
                    return;
                }
                in[next]--;
                if (in[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (answers.size() < n) {
            System.out.println(-1);
            return;
        }

        final String answer = answers.stream()
            .map(Objects::toString)
            .collect(Collectors.joining(" "));
        System.out.println(answer);
    }

    private static class ListMap<K, V> extends HashMap<K, List<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public List<V> getList(final K key) {
            return Optional.ofNullable(super.get(key)).orElse(Collections.emptyList());
        }
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
