package AtCoder.other.typecal90.bb054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
頑張って比較する回数を減らす
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final ListMap<Integer, Integer> coauthors = new ListMap<>(); // key: index of given list, value: author number
        final ListMap<Integer, Integer> invertedIndex = new ListMap<>(); // key: author number, value: index of given list
        for (int i = 0; i < m; i++) {
            final int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                final int r = scanner.nextInt();
                coauthors.putSingle(i, r);
                invertedIndex.putSingle(r, i);
            }
        }
        final int[] researcherValues = new int[n + 1];
        Arrays.fill(researcherValues, -1);
        researcherValues[1] = 0;
        final Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        final boolean[] visitedIndex = new boolean[m];
        while (!queue.isEmpty()) {
            final int poll = queue.poll();
            for (final int index : invertedIndex.getList(poll)) {
                if (visitedIndex[index]) {
                    continue;
                }
                visitedIndex[index] = true;
                for (final int author : coauthors.getList(index)) {
                    if (researcherValues[author] == -1) {
                        researcherValues[author] = researcherValues[poll] + 1;
                        queue.add(author);
                    }
                }
            }
        }

        final String answer = Arrays.stream(researcherValues, 1, n + 1)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining("\n"));
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
}
