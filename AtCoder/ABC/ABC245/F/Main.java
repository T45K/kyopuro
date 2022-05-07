package AtCoder.ABC.ABC245.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
「出ていく辺がない -> 行き止まり -> それ以上移動できない」になる
また、行き止まりである頂点に向かう辺しかない頂点も同様に条件に合わない頂点になる
なので、出ていく辺がない頂点を取り除く -> 出ていく辺の数を再計算する -> 出ていく辺がない頂点を取り除く -> ...
を繰り返す

トポロジカルソートに似た発想
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, Set<Integer>> parentToChildMap = new HashMap<>();
        final Map<Integer, List<Integer>> childToParentMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            parentToChildMap.computeIfAbsent(u, k -> new HashSet<>()).add(v);
            childToParentMap.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        final Deque<Integer> queue = IntStream.rangeClosed(1, n)
            .filter(i -> !parentToChildMap.containsKey(i))
            .boxed()
            .collect(Collectors.toCollection(ArrayDeque::new));

        while (!queue.isEmpty()) {
            final int childToBeDeleted = queue.pollFirst();
            for (final int parent : childToParentMap.getOrDefault(childToBeDeleted, Collections.emptyList())) {
                parentToChildMap.get(parent).remove(childToBeDeleted);
                if (parentToChildMap.get(parent).isEmpty()) {
                    parentToChildMap.remove(parent);
                    queue.add(parent);
                }
            }
        }

        System.out.println(parentToChildMap.size());
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
