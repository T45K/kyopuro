package ABC.ABC209.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO
public class Main {
    private static final String TAKAHASHI = "Takahashi";
    private static final String AOKI = "Aoki";
    private static final String DRAW = "Draw";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<String> list = Stream.generate(scanner::next)
            .limit(n)
            .collect(Collectors.toList());
        final List<String> sortedPrefix = list.stream().sorted(Comparator.comparing(Function.identity()))
            .map(str -> str.substring(0, 3))
            .collect(Collectors.toList());
//        final List<Pair> sorted = IntStream.range(0, n)
//            .mapToObj(i -> new Pair(i, scanner.next()))
//            .sorted(Comparator.comparing(p -> p.value))
//            .collect(Collectors.toList());
        final Set<Integer> devNull = new HashSet<>();
        final String[] answers = new String[n];
        for (int i = 0; i < list.size(); i++) {
            final String value = list.get(i);
            final String suffix = value.substring(value.length() - 3);
            final int index = Collections.binarySearch(sortedPrefix, suffix);
            if (index == -1) {
                devNull.add(i);
                answers[i] = DRAW;
            }
        }

    }

    private static class ListMap<K, V> extends HashMap<K, List<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public List<V> getList(final K key) {
            return Optional.ofNullable(super.get(key)).orElse(Collections.emptyList());
        }
    }

    private static class Pair {
        final int index;
        final String value;

        Pair(final int index, final String value) {
            this.index = index;
            this.value = value;
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
