package AtCoder.ABC.ABC296.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 入次数は不定
// 出次数は必ず1
// 任意のループ回で辿り着ければok
// 閉路内は必ず可能
// 「閉路じゃないフラグ」 = 「入次数がない」|「閉路じゃないフラグの頂点からの入次数しかない」
// それはトポロジカルソート
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, Integer> naturalGraph = new HashMap<>();
        final MultiValueMap<Integer, Integer> reverseGraph = new MultiValueMap<>();
        for (int i = 1; i <= n; i++) {
            final int a = scanner.nextInt();
            naturalGraph.put(i, a);
            reverseGraph.add(a, i);
        }

        final Deque<Integer> queue = IntStream.rangeClosed(1, n)
            .boxed()
            .filter(Predicate.not(reverseGraph::containsKey))
            .collect(Collectors.toCollection(ArrayDeque::new));

        while (!queue.isEmpty()) {
            final Integer poll = queue.pollFirst();
            final Integer next = naturalGraph.get(poll);
            final Set<Integer> inNodes = reverseGraph.get(next);
            inNodes.remove(poll);
            if (inNodes.isEmpty()) {
                queue.addLast(next);
            }
        }

        final long answer = reverseGraph.values().stream()
            .filter(Predicate.not(Set::isEmpty))
            .count();
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
