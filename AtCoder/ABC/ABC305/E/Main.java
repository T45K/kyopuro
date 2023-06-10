package AtCoder.ABC.ABC305.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();
        final MultiValueMap<Integer, Integer> graph = new MultiValueMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            graph.add(a, b);
            graph.add(b, a);
        }

        final List<GuardMan> guardManList = Stream.generate(() -> new GuardMan(scanner.nextInt(), scanner.nextInt()))
            .limit(k)
            .collect(Collectors.toList());

        final boolean[] isVisited = new boolean[n + 1];
        final PriorityQueue<Element> queue = new PriorityQueue<>(Comparator.comparing(e -> e.restHp, Comparator.reverseOrder()));
        for (final GuardMan guardMan : guardManList) {
            queue.add(new Element(guardMan.node, guardMan.hp));
        }
        while (!queue.isEmpty()) {
            final Element element = queue.poll();
            final int restHp = element.restHp;
            final int node = element.node;
            if (isVisited[node]) {
                continue;
            }
            isVisited[node] = true;
            if (restHp == 0) {
                continue;
            }

            for (final int nextNode : graph.get(node)) {
                if (isVisited[nextNode]) {
                    continue;
                }
                queue.add(new Element(nextNode, restHp - 1));
            }
        }

        final List<String> answers = IntStream.rangeClosed(1, n)
            .filter(i -> isVisited[i])
            .mapToObj(Integer::toString)
            .collect(Collectors.toList());
        System.out.println(answers.size());
        System.out.println(String.join(" ", answers));
    }

    private static class GuardMan {
        final int node;
        final int hp;

        GuardMan(final int node, final int hp) {
            this.node = node;
            this.hp = hp;
        }
    }

    private static class Element {
        final int node;
        final int restHp;

        Element(final int node, final int restHp) {
            this.node = node;
            this.restHp = restHp;
        }
    }

    private static class MultiValueMap<K, V> extends HashMap<K, List<V>> {

        public void add(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        @Override
        public List<V> get(final Object key) {
            return super.getOrDefault(key, Collections.emptyList());
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
