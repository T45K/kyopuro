package ABC.ABC303.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final MultiValueMap<Integer, Integer> tree = new MultiValueMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            tree.add(u, v);
            tree.add(v, u);
        }

        final Deque<Integer> terminalNodesOfStars = new ArrayDeque<>();
        terminalNodesOfStars.add(IntStream.rangeClosed(1, n)
            .filter(i -> tree.get(i).size() == 1)
            .findFirst()
            .orElseThrow());

        final List<Integer> answers = new ArrayList<>();
        while (!terminalNodesOfStars.isEmpty()) {
            final int poll = terminalNodesOfStars.pollFirst();
            final int centerNodeOfCurrentStar = tree.get(poll).iterator().next();
            final int level = tree.get(centerNodeOfCurrentStar).size();
            answers.add(level);

            for (final int terminalNodeOfCurrentStar : tree.get(centerNodeOfCurrentStar)) {
                final Set<Integer> connectedNodes = tree.get(terminalNodeOfCurrentStar);
                final boolean isConnectedToAnotherStar = connectedNodes.size() == 2;
                if (!isConnectedToAnotherStar) {
                    tree.remove(terminalNodeOfCurrentStar);
                    continue;
                }

                connectedNodes.remove(centerNodeOfCurrentStar);
                final int terminalNodeOfAnotherStar = connectedNodes.iterator().next();
                tree.get(terminalNodeOfAnotherStar).remove(terminalNodeOfCurrentStar);
                terminalNodesOfStars.addLast(terminalNodeOfAnotherStar);
            }

            tree.remove(centerNodeOfCurrentStar);
        }

        final String answer = answers.stream()
            .sorted()
            .map(Objects::toString)
            .collect(Collectors.joining(" "));
        System.out.println(answer);
    }

    private static class MultiValueMap<K, V> extends HashMap<K, Set<V>> {

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
